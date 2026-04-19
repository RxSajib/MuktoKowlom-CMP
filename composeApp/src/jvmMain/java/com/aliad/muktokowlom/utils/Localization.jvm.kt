package com.aliad.muktokowlom.utils

import java.util.Locale

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Localization {
    actual fun setLocal(iso: String) {

        val locale = Locale.forLanguageTag(iso)

        // JVM default locale set
        Locale.setDefault(locale)
    }
}