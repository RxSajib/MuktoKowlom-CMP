package com.aliad.muktokowlom.ui.screen.publishedPendingStoryList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.LocalPlatformContext
import com.aliad.muktokowlom.ui.component.EmptyStoryMessage
import com.aliad.muktokowlom.ui.component.LoadStateAppendError
import com.aliad.muktokowlom.ui.component.Loader
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.ServerError
import com.aliad.muktokowlom.ui.component.StoryItem
import com.aliad.muktokowlom.ui.component.StoryLoaderShimmer
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.presentation.signIn.ui.publishedPendingStory.PublishedPendingStoryViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.pending
import muktokowlomcmp.composeapp.generated.resources.published_story
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PublishedPendingStoryListScreen(
    backStack: NavBackStack<NavKey>,
    rootBackStack: NavBackStack<NavKey>,
    viewModel: SharedViewModel,
) {
 //okk khat
    val publishedPendingViewModel : PublishedPendingStoryViewModel = koinViewModel()
    val pendingStory = publishedPendingViewModel.pendingStory.collectAsLazyPagingItems()
    val isPendingStory = viewModel.pendingStoryState.collectAsStateWithLifecycle()
    val selectedLan = publishedPendingViewModel.getSelectedLn.collectAsStateWithLifecycle("en")
    val pagingUiState = publishedPendingViewModel.pagingUiState.collectAsStateWithLifecycle()
    val context = LocalPlatformContext.current

    LaunchedEffect(pendingStory.loadState) {
        publishedPendingViewModel.updatePagingLoadStates(
            loadStates = pendingStory.loadState,
            itemCount = pendingStory.itemCount
        )
    }

    Scaffold(
        topBar = {
            MyCustomAppBar(
                onBackPress = {
                    rootBackStack.removeLastOrNull()
                },
                title = if (isPendingStory.value) stringResource(Res.string.pending) else stringResource(
                    Res.string.published_story
                ),
                editProfile = {})
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
                .padding(innerPadding).padding(16.dp)
        ) {

                if (pagingUiState.value.isRefreshing) {

                    StoryLoaderShimmer()

                }
                else if (pagingUiState.value.refreshError != null) {
                    ServerError {
                        pendingStory.retry()
                    }
                }
                else if (pagingUiState.value.isEmpty) {
                    EmptyStoryMessage(
                        retryAgain = {
                            pendingStory.retry()
                        }
                    )
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        items(pendingStory.itemCount) { position ->
                            StoryItem(selectedLan = selectedLan.value,  pendingStory[position], context = context) { bookItem ->
                            //    sharedViewModel.selectedBookID = bookItem.storyID?: 0
                             //   backStack.add(AppDestination.StoryDetails)
                            }
                        }

                        if (pagingUiState.value.isAppending) {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Loader()
                                }
                            }
                        }

                        pagingUiState.value.appendError?.let {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                LoadStateAppendError(retry = { pendingStory.retry() })
                            }
                        }

                    }
            }
        }
    }
}