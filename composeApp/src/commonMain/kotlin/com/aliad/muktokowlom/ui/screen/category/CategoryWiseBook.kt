package com.aliad.muktokowlom.ui.screen.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.collectAsLazyPagingItems
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.presentation.signIn.ui.categoryWiseBook.CategoryWiseBookViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoryWiseBook(backStack: NavBackStack<NavKey>, category: AppDestination.CategoryWiseBook) {

    val viewModel : CategoryWiseBookViewModel = koinViewModel()
    val x = viewModel.data.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = category.category?.name?: "",
                isBackButtonEnable = true,
                onBackPress = { backStack.remove(AppDestination.CategoryWiseBook(category = category.category)) })
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(x.itemCount){
                Text("${x[it]?.category_name_bn}")
            }
        }
    }
}

