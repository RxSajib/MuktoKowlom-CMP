package com.aliad.muktokowlom.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Localization(private val context: Context) {
    actual fun setLocal(iso: String) {




       val locale = Locale.forLanguageTag(iso)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(iso)
        )

       context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}