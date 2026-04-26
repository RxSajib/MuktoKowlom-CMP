package com.aliad.muktokowlom.ui.category

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.component.EmptyStoryMessage
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.LoadStateAppendError
import com.aliad.muktokowlom.ui.component.LoadStateRefreshError
import com.aliad.muktokowlom.ui.component.Loader
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.component.ShimmerBox
import com.aliad.muktokowlom.ui.component.StoryItem
import com.aliad.muktokowlom.ui.component.StoryLoaderShimmer
import com.aliad.presentation.signIn.ui.categoryWiseBook.CategoryWiseBookViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_your_favourite_genre
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

private const val TAG = "CategoryWiseBook"

@Composable
fun CategoryWiseBook(
    backStack: NavBackStack<NavKey>,
    sharedViewModel: SharedViewModel,
    rootBackStack: NavBackStack<NavKey>
) {

    val viewModel: CategoryWiseBookViewModel = koinViewModel()
    val storyItem = viewModel.categoryWiseSearch.collectAsLazyPagingItems()
    val pagingUiState = viewModel.pagingUiState.collectAsState()
    val selectedStory = sharedViewModel.selectedCategory.collectAsStateWithLifecycle()

     print("selected category id ${selectedStory.value.id}")


    LaunchedEffect(storyItem.loadState) {
        viewModel.updatePagingLoadStates(storyItem.loadState, storyItem.itemCount)
    }

    Surface(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
    ) {


        Scaffold(
            topBar = {
                _root_ide_package_.com.aliad.muktokowlom.ui.component.MyCustomAppBar(
                    title = selectedStory.value.name,
                    isBackButtonEnable = true,
                    onBackPress = {
                        if (!rootBackStack.contains(AppDestination.Dest.CategoryWiseBook)) {
                            backStack.removeLastOrNull()
                        } else {
                            rootBackStack.removeLastOrNull()
                        }
                    },
                    editProfile = {},
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp)
            ) {
                _root_ide_package_.com.aliad.muktokowlom.ui.component.MyCustomInputFiled(
                    placeHolderText = stringResource(Res.string.search_your_favourite_genre),
                    text = viewModel.searchStoryData,
                    onValueChange = { firstNameInput ->
                        viewModel.searchStoryData = firstNameInput

                        if (firstNameInput.isEmpty()) {
                            viewModel.categoryByBook(
                                search = "All",
                                categoryID = selectedStory.value.id
                            )
                        }
                    },
                    isPasswordInput = false,
                    isVisiblePasswordChange = {},
                    isSearchEnable = true,
                    isPasswordVisibility = true,
                    leftIcon = painterResource(Res.drawable.search_alt_svgrepo_com),
                    onSearch = { searchKey ->
                        viewModel.categoryByBook(
                            search = searchKey,
                            categoryID = selectedStory.value.id
                        )
                    }
                ) {}
                _root_ide_package_.com.aliad.muktokowlom.ui.component.HeightGap(height = 10.dp)
                Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    if (pagingUiState.value.isRefreshing) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            _root_ide_package_.com.aliad.muktokowlom.ui.component.StoryLoaderShimmer()
                        }
                    }
                   else if (pagingUiState.value.refreshError != null) {
                        _root_ide_package_.com.aliad.muktokowlom.ui.component.LoadStateRefreshError(
                            onRetry = { storyItem.retry() })

                    }
                   else if (pagingUiState.value.isEmpty) {
                        _root_ide_package_.com.aliad.muktokowlom.ui.component.EmptyStoryMessage()
                    } else {


                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            items(storyItem.itemCount) { position ->
                                _root_ide_package_.com.aliad.muktokowlom.ui.component.StoryItem(
                                    storyItem[position]
                                ) { bookItem ->
                                    backStack.add(AppDestination.StoryDetails(myBookItem = bookItem))
                                }
                            }

                            if (pagingUiState.value.isAppending) {
                                item(
                                    span = { GridItemSpan(maxLineSpan) }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        _root_ide_package_.com.aliad.muktokowlom.ui.component.Loader()
                                    }
                                }
                            }

                            pagingUiState.value.appendError?.let {
                                item(
                                    span = { GridItemSpan(maxLineSpan) }
                                ) {
                                    _root_ide_package_.com.aliad.muktokowlom.ui.component.LoadStateAppendError(
                                        retry = { storyItem.retry() })
                                }
                            }


                        }
                    }

                }
            }
        }
    }
}

