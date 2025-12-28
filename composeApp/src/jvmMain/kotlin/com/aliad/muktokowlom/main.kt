package com.aliad.muktokowlom

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aliad.muktokowlom.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MuktoKowlomCMP",
    ) {
        App()
    }
}