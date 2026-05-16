package com.aliad.muktokowlom.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.LocalPlatformContext
import com.aliad.model.MyCategory
import com.aliad.muktokowlom.ui.component.CategoryItem
import com.aliad.muktokowlom.ui.component.CategoryScreenShimmer
import com.aliad.muktokowlom.ui.component.ServerError
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.presentation.signIn.ui.category.CategoryViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import com.aliad.presentation.utils.UiState
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoryScreen(backStack: NavBackStack<NavKey>, sharedViewModel: SharedViewModel) {
    val viewModel: CategoryViewModel = koinViewModel()
    val categoryData = viewModel.categoryData.collectAsStateWithLifecycle()
    val context = LocalPlatformContext.current


    val refreshState = rememberRefreshLayoutState {
        setRefreshState(RefreshContentStateEnum.Refreshing)
        viewModel.fetchCategory()
    }

    LaunchedEffect(viewModel.isLoading) {
        if (!viewModel.isLoading) {
            refreshState.setRefreshState(state = RefreshContentStateEnum.Stop)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {

        PullToRefresh(refreshLayoutState = refreshState) {

            when(categoryData.value){
                is UiState.Loading -> {
                    CategoryScreenShimmer()
                }

                is UiState.Success -> {
                    val data = (categoryData.value as UiState.Success<List<MyCategory>>).data

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(data) { categoryData ->
                            CategoryItem(
                                category = categoryData,
                                context = context,
                                onClick = {
                                    sharedViewModel.setCategory(category = categoryData)
                                    backStack.add(
                                        AppDestination.Dest(
                                            AppDestination.Dest.CategoryWiseBook::class.simpleName
                                                ?: ""
                                        )
                                    )
                                })
                        }
                    }
                }
                is UiState.Error -> {
                    ServerError {
                        viewModel.fetchCategory()
                    }
                }

            }
        }
    }

}