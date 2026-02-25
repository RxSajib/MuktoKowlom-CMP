package com.aliad.muktokowlom

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.aliad.muktokowlom.ui.navigation.root.RootNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        RootNavigation()
    }
}