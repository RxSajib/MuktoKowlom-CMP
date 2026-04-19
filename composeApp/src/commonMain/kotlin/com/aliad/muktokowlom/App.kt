package com.aliad.muktokowlom

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.aliad.muktokowlom.ui.navigation.root.RootNavigation
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {

    MaterialTheme {
        RootNavigation()
    }
}