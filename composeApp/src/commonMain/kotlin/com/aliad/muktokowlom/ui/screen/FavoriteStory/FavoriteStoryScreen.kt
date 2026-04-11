package com.aliad.muktokowlom.ui.screen.FavoriteStory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.presentation.signIn.ui.FavoriteStory.FavoriteStoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteStoryScreen(backStack: NavBackStack<NavKey>) {

    val viewModel : FavoriteStoryViewModel = koinViewModel()

    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(
                    text = "FavoriteStory",
                )
            }
    }
}