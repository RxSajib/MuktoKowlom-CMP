package com.aliad.muktokowlom.ui.screen.publishedStory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.LoadStateAppendError
import com.aliad.muktokowlom.ui.component.Loader
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.component.ServerError
import com.aliad.muktokowlom.ui.component.StoryItem
import com.aliad.muktokowlom.ui.component.StoryLoaderShimmer
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.presentation.signIn.ui.liveStory.LiveStoryListViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.published_story
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_your_favourite_genre
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PublishedStoryScreen(rootBackStack: NavBackStack<NavKey>, backStack: NavBackStack<NavKey>) {

    val viewModel : LiveStoryListViewModel = koinViewModel()
    val liveStoryPagingData = viewModel.liveStory.collectAsLazyPagingItems()
    val selectedLan = viewModel.selectedLan.collectAsStateWithLifecycle("en")
    val pagingUiState = viewModel.pagingUiState.collectAsStateWithLifecycle()
    val contextCoil = LocalPlatformContext.current


    LaunchedEffect(liveStoryPagingData.loadState) {
        viewModel.updatePagingLoadStates(
            loadStates = liveStoryPagingData.loadState,
            itemCount = liveStoryPagingData.itemCount
        )
    }

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.published_story),
                    editProfile = {},
                    onBackPress = {
                        rootBackStack.removeLastOrNull()
                    }
                )
            }
        ) {innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface).padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp)
            ) {



                Box(modifier = Modifier.fillMaxSize()) {

                    if (pagingUiState.value.isRefreshing) {

                        StoryLoaderShimmer()

                    }
                    else if (pagingUiState.value.refreshError != null) {
                        ServerError {
                            liveStoryPagingData.retry()
                        }
                    }
                    else if (pagingUiState.value.isEmpty) {
                        EmptyStoryMessage(
                            retryAgain = {
                                liveStoryPagingData.retry()
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

                            items(liveStoryPagingData.itemCount) { position ->
                                StoryItem(selectedLan = selectedLan.value,  liveStoryPagingData[position], context = contextCoil) { bookItem ->
                                 //   viewModel.selectedBookID = bookItem.storyID?: 0
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
                                    LoadStateAppendError(retry = { liveStoryPagingData.retry() })
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

