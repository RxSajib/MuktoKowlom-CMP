package com.aliad.muktokowlom.ui.screen.allRelease

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.presentation.signIn.ui.allReleaseStory.AllReleaseStoryViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.all_release
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_your_favourite_genre
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AllStoryScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel : AllReleaseStoryViewModel = koinViewModel()

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.all_release),
                    editProfile = {},
                    onBackPress = {
                        rootBackStack.removeLastOrNull()
                    }
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(start = 16.dp, end = 16.dp)) {

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