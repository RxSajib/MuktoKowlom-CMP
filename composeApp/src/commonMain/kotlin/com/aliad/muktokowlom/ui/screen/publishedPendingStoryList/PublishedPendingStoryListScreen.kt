package com.aliad.muktokowlom.ui.screen.publishedPendingStoryList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.pending
import muktokowlomcmp.composeapp.generated.resources.published_story
import org.jetbrains.compose.resources.stringResource

@Composable
fun PublishedPendingStoryListScreen(
    backStack: NavBackStack<NavKey>,
    rootBackStack: NavBackStack<NavKey>,
    viewModel: SharedViewModel,
) {


    val isPendingStory = viewModel.pendingStoryState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MyCustomAppBar(onBackPress = {
                rootBackStack.removeLastOrNull()
            }, title = if(isPendingStory.value ) stringResource(Res.string.pending) else stringResource(Res.string.published_story), editProfile = {})
        }
    ){ innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            Text(
                text = "Published Pending Story List Screen ${isPendingStory.value}"
            )
        }
    }
}