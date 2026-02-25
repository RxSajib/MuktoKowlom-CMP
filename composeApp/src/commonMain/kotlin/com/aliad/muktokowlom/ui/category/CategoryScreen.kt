package com.aliad.muktokowlom.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.CategoryItem
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.presentation.signIn.ui.category.CategoryViewModel
import io.ktor.http.HttpHeaders.Destination
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.all_category
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoryScreen(backStack: NavBackStack<NavKey>) {
    val viewModel: CategoryViewModel = koinViewModel()
    val categoryData = viewModel.categoryData.collectAsStateWithLifecycle()



    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(categoryData.value) { categoryData ->
                CategoryItem(category = categoryData, onClick = {
                    backStack.add(
                        AppDestination.Dest(
                            firstDestName = AppDestination.Dest.CategoryWiseBook::class.simpleName
                                ?: ""
                        )
                    )
                })
            }
        }
    }

}