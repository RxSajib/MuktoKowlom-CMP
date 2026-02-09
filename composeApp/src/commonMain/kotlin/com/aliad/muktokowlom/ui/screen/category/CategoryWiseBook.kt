package com.aliad.muktokowlom.ui.screen.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.EmptyStoryMessage
import com.aliad.muktokowlom.ui.screen.component.LoadStateAppendError
import com.aliad.muktokowlom.ui.screen.component.LoadStateRefreshError
import com.aliad.muktokowlom.ui.screen.component.Loader
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.StoryItem
import com.aliad.presentation.signIn.ui.categoryWiseBook.CategoryWiseBookViewModel
import org.koin.compose.viewmodel.koinViewModel

private const val TAG = "CategoryWiseBook"
@Composable
fun CategoryWiseBook(backStack: NavBackStack<NavKey>, category: AppDestination.CategoryWiseBook) {

    val viewModel: CategoryWiseBookViewModel = koinViewModel()
    val storyItem = viewModel.data.collectAsLazyPagingItems()
    val pagingUiState = viewModel.pagingUiState.collectAsState()


    LaunchedEffect(storyItem.loadState) {
        viewModel.updatePagingLoadStates(storyItem.loadState, storyItem.itemCount)
    }


    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = category.category?.name ?: "",
                isBackButtonEnable = true,
                onBackPress = { backStack.remove(AppDestination.CategoryWiseBook(category = category.category)) }, editProfile = {})
        }
    ) {innerPadding ->

        if (pagingUiState.value.isRefreshing) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if(pagingUiState.value.refreshError != null){
            LoadStateRefreshError(onRetry = {storyItem.retry()})

        }
        if(pagingUiState.value.isEmpty){
            EmptyStoryMessage()
        }
        else {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                columns = GridCells.Fixed(2)
            ) {

                items(storyItem.itemCount) { position ->
                    StoryItem(storyItem[position]){bookItem ->

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
                            Loader()
                        }
                    }
                }

                pagingUiState.value.appendError?.let {
                    item(
                        span = {GridItemSpan(maxLineSpan)}
                    ) {
                        LoadStateAppendError(retry = {storyItem.retry()})
                    }
                }

            }
        }
    }
}

