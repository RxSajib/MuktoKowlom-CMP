package com.aliad.muktokowlom.platform

import com.aliad.muktokowlom.utils.Localization
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun localModule(): Module {
    return module {
        single<Localization> {
            Localization(context = androidContext())
        }
    }
}
