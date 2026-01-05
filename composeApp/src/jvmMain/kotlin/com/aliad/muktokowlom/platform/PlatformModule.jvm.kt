package com.aliad.muktokowlom.platform

import com.aliad.dataSource.createDataStore
import com.aliad.dataSource.dataStoreFileName
import kotlinx.coroutines.NonCancellable.get
import org.koin.core.module.Module
import org.koin.dsl.module
import java.nio.file.Files
import java.nio.file.Paths
import javax.naming.Context

actual fun platformModule(): Module {
   return module {
       createDataStore(
           producerPath = {
               val dir = Paths.get(
                   System.getProperty("user.home"),
                   ".MuktoKowlomCMP"
               )

               Files.createDirectories(dir) // ✅ important

               dir.resolve(dataStoreFileName)
                   .toAbsolutePath()
                   .toString()
           }
       )
   }
}