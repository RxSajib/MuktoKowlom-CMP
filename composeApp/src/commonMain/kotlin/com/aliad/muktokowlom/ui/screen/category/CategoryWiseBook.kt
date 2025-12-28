package com.aliad.muktokowlom.ui.screen.category

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar

@Composable
fun CategoryWiseBook(backStack: NavBackStack<NavKey>, category: AppDestination.CategoryWiseBook) {
    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = category.category?.name?: "",
                isBackButtonEnable = true,
                onBackPress = { backStack.remove(AppDestination.CategoryWiseBook(category = category.category)) })
        }
    ) {

    }
}

