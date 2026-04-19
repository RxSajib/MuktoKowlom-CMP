package com.aliad.muktokowlom.utils

import java.util.Locale

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Localization {
    actual fun setLocal(iso: String) {

        print("local $iso")

        val locale = Locale.forLanguageTag(iso)
        Locale.setDefault(locale)

    }
}