package com.aliad.muktokowlom.ui.screen.searchStory

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
import androidx.compose.material3.Text
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
import com.aliad.muktokowlom.ui.component.EmptyStoryMessage
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.LoadStateAppendError
import com.aliad.muktokowlom.ui.component.LoadStateRefreshError
import com.aliad.muktokowlom.ui.component.Loader
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.component.StoryItem
import com.aliad.muktokowlom.ui.component.StoryLoaderShimmer
import com.aliad.presentation.signIn.ui.searchStoryResult.SearchStoryResultViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_your_favourite_genre
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchStoryResultScreen(
    backStack: NavBackStack<NavKey>,
    rootBackStack: NavBackStack<NavKey>,
    sharedViewModel: SharedViewModel,
) {

    val viewModel : SearchStoryResultViewModel = koinViewModel()
    val storyData = viewModel.searchStory.collectAsLazyPagingItems()
    val pagingUiState = viewModel.pagingUiState.collectAsState()
    val selectedLan = viewModel.selectedLan.collectAsState("en")

    LaunchedEffect(storyData.loadState){
        viewModel.updatePagingLoadStates( loadStates = storyData.loadState, itemCount = storyData.itemCount)
    }

    LaunchedEffect(sharedViewModel.searchKeyword){
        if(!viewModel.readFirstTimeSearchValue){
            viewModel.searchStoryData = sharedViewModel.searchKeyword
            viewModel.searchStory(search = sharedViewModel.searchKeyword)
            viewModel.readFirstTimeSearchValue = true
        }
    }

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(onBackPress = {
                    rootBackStack.removeLastOrNull()
                }, title = "Search Result", editProfile = {})
            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface).padding(innerPadding).padding(16.dp)) {
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
                    isVisiblePasswordChange = {
                    },
                    isSearchEnable = true,
                    isPasswordVisibility = true,
                    leftIcon = painterResource(Res.drawable.search_alt_svgrepo_com),
                    modifier = Modifier,
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
                        EmptyStoryMessage(
                            retryAgain = {storyData.retry()}
                        )
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
                                StoryItem(selectedLan = selectedLan.value, item = storyData[position]){bookItem ->
                                    sharedViewModel.selectedBookID = bookItem.storyID?: 0
                                    backStack.add(AppDestination.StoryDetails)
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
