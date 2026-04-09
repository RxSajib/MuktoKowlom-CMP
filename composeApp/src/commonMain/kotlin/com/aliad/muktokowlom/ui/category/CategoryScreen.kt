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
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.CategoryItem
import com.aliad.muktokowlom.ui.screen.component.CategoryScreenShimmer
import com.aliad.presentation.signIn.ui.category.CategoryViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoryScreen(backStack: NavBackStack<NavKey>, sharedViewModel: SharedViewModel) {
    val viewModel: CategoryViewModel = koinViewModel()
    val categoryData = viewModel.categoryData.collectAsStateWithLifecycle()


    val refreshState = rememberRefreshLayoutState {
        setRefreshState(RefreshContentStateEnum.Refreshing)
        viewModel.fetchCategory()
    }

    LaunchedEffect(viewModel.isLoading){
        if(!viewModel.isLoading){
            refreshState.setRefreshState(state = RefreshContentStateEnum.Stop)
        }
    }


    PullToRefresh(refreshLayoutState = refreshState){

    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {

        if (viewModel.isLoading) {
            CategoryScreenShimmer()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(categoryData.value) { categoryData ->
                    CategoryItem(category = categoryData, onClick = {
                        sharedViewModel.setCategory(category = categoryData)
                        backStack.add(AppDestination.Dest(AppDestination.Dest.CategoryWiseBook::class.simpleName?: ""))
                    })
                }
            }
        }
    }
    }

}