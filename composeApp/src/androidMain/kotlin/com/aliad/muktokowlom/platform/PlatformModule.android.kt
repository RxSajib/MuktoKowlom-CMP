package com.aliad.muktokowlom.platform

import android.content.Context
import com.aliad.dataSource.createDataStore
import com.aliad.dataSource.dataStoreFileName
import com.aliad.muktokowlom.app.MyApplication
import com.aliad.muktokowlom.utils.Localization
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single {
            createDataStore(
                producerPath = {
                    MyApplication.appContext.filesDir.resolve(dataStoreFileName).absolutePath
                }
            )
        }
    }
}
