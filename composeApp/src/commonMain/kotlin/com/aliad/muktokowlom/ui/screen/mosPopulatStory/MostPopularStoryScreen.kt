package com.aliad.muktokowlom.ui.screen.mosPopulatStory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.presentation.signIn.ui.mostPopularStory.MostPopularStoryViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.most_popular
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_your_favourite_genre
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MostPopularStoryScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {
    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {

        val viewModel : MostPopularStoryViewModel = koinViewModel()

        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.most_popular),
                    onBackPress = {
                        rootBackStack.removeLastOrNull()
                    },
                    editProfile = {}
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp)) {
                MyCustomInputFiled(
                    placeHolderText = stringResource(Res.string.search_your_favourite_genre),
                    text = viewModel.searchStoryData,
                    onValueChange = { firstNameInput ->
                        viewModel.searchStoryData = firstNameInput
                    },
                    isPasswordInput = false,
                    isVisiblePasswordChange = {},
                    isSearchEnable = true,
                    isPasswordVisibility = true,
                    leftIcon = painterResource(Res.drawable.search_alt_svgrepo_com),
                    onSearch = {

                    }
                ) {}
            }
        }
    }
}