package com.aliad.muktokowlom.platform

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
       createDataStore(
           producerPath = {
               "${getDocumentPath()}/$dataStoreFileName"
           }
       )
   }
}

@OptIn(ExperimentalForeignApi::class)
fun getDocumentPath() : String {
    val documentPath = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentPath!!.path)
}