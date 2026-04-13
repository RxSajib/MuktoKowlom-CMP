package com.aliad.muktokowlom.ui.screen.newRelease

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.collectAsLazyPagingItems
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.EmptyStoryMessage
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.LoadStateAppendError
import com.aliad.muktokowlom.ui.screen.component.LoadStateRefreshError
import com.aliad.muktokowlom.ui.screen.component.Loader
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.screen.component.StoryItem
import com.aliad.muktokowlom.ui.screen.component.StoryLoaderShimmer
import com.aliad.presentation.signIn.ui.newReleaseStory.NewReleaseStoryViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.new_release
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_your_favourite_genre
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewReleaseScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel : NewReleaseStoryViewModel = koinViewModel()
    val storyData = viewModel.storyData.collectAsLazyPagingItems()
    val pagingUiState = viewModel.pagingUiState.collectAsState()

    LaunchedEffect(storyData.loadState){
        viewModel.updatePagingLoadStates( loadStates = storyData.loadState, itemCount = storyData.itemCount)
    }

    Surface(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
    ) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.new_release), onBackPress = {
                        rootBackStack.removeLastOrNull()
                    },
                    editProfile = {}
                )
            }
        ) {innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp)) {
                MyCustomInputFiled(
                    placeHolderText = stringResource(Res.string.search_your_favourite_genre),
                    text = viewModel.searchStoryData,
                    onValueChange = { firstNameInput ->
                        viewModel.searchStoryData = firstNameInput
                        if(firstNameInput.isEmpty()){
                            viewModel.searchStory(search = "All")
                        }
                    },
                    isPasswordInput = false,
                    isVisiblePasswordChange = {},
                    isSearchEnable = true,
                    isPasswordVisibility = true,
                    leftIcon = painterResource(Res.drawable.search_alt_svgrepo_com),
                    onSearch = {searchKey ->
                        viewModel.searchStory(search = searchKey)
                    }
                ) {}

                HeightGap(height = 10.dp)
                Box(modifier = Modifier.fillMaxWidth().weight(1f)) {

                    if (pagingUiState.value.isRefreshing) {

                        StoryLoaderShimmer()

                    }
                    else if(pagingUiState.value.refreshError != null){
                        LoadStateRefreshError(onRetry = {storyData.retry()})

                    }
                    else if(pagingUiState.value.isEmpty){
                        EmptyStoryMessage()
                    }
                    else {
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            items(storyData.itemCount) { position ->
                                StoryItem(storyData[position]){bookItem ->
                                    backStack.add(AppDestination.StoryDetails(myBookItem = bookItem))
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
                                    span = {GridItemSpan(maxLineSpan)}
                                ) {
                                    LoadStateAppendError(retry = {storyData.retry()})
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}