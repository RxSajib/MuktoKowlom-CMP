package com.aliad.muktokowlom.platform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.aliad.dataSource.createDataStore
import com.aliad.dataSource.dataStoreFileName
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun platformModule(): Module {
   return module {
       single<DataStore<Preferences>> {
           createDataStore(
               producerPath = { "${getDocumentPath()}/$dataStoreFileName" }
           )
       }
   }
}

@OptIn(ExperimentalForeignApi::class)
fun getDocumentPath(): String {
    val url = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = true,
        error = null
    ) ?: error("Could not resolve Documents directory")
    return url.path ?: error("Documents URL has no path")
}
