package com.aliad.muktokowlom.ui.screen.storyView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar

@Composable
fun StoryViewScreen(backStack: NavBackStack<NavKey>) {

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(onBackPress = {backStack.removeLastOrNull()}, title = "Story View", editProfile = {})
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

                val pdfUrl = "https://pdfobject.com/pdf/sample.pdf"


                LoadPdf(pdfUrl)
            }
        }
    }
}

@Composable
expect fun LoadPdf(pdfUrl: String)