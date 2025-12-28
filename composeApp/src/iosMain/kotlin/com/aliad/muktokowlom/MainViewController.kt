package com.aliad.muktokowlom

import androidx.compose.ui.window.ComposeUIViewController
import com.aliad.muktokowlom.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()

    App()
}