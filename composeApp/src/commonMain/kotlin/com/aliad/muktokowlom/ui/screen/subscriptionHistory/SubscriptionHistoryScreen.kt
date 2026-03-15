package com.aliad.muktokowlom.ui.screen.subscriptionHistory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material3.Scaffold
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
import com.aliad.muktokowlom.ui.screen.component.EmptyStoryMessage
import com.aliad.muktokowlom.ui.screen.component.LoadStateAppendError
import com.aliad.muktokowlom.ui.screen.component.LoadStateRefreshError
import com.aliad.muktokowlom.ui.screen.component.Loader
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.StoryLoaderShimmer
import com.aliad.muktokowlom.ui.screen.component.SubscriptionHistoryItem
import com.aliad.muktokowlom.ui.screen.component.SubscriptionShimmer
import com.aliad.presentation.signIn.ui.subscriptionHistory.SubscriptionHistoryViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.subscription_history
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SubscriptionHistoryScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel : SubscriptionHistoryViewModel = koinViewModel()
    val subscriptionHistory = viewModel.getSubscriptionHistory().collectAsLazyPagingItems()

    val pagingUiState = viewModel.pagingUiState.collectAsState()


    LaunchedEffect(subscriptionHistory.loadState) {
        viewModel.updatePagingLoadStates(subscriptionHistory.loadState, subscriptionHistory.itemCount)
    }

    Scaffold(
        topBar = {
            MyCustomAppBar(
                onBackPress = {   try {
                    if (backStack.size > 1) {
                        backStack.removeLastOrNull()
                    }else {
                        rootBackStack.removeLastOrNull()
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                } },
                title = stringResource(Res.string.subscription_history),
                editProfile = {})
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

            LazyColumn {
                items(subscriptionHistory.itemSnapshotList.items.size) { position ->
                    SubscriptionHistoryItem(payment = subscriptionHistory.itemSnapshotList.items[position])
                }

                /*  if (pagingUiState.value.isRefreshing) {

                SubscriptionShimmer()

            }
            if(pagingUiState.value.refreshError != null){
                LoadStateRefreshError(onRetry = {subscriptionHistory.retry()})

            }
            if(pagingUiState.value.isEmpty){
                EmptyStoryMessage()
            }else{
                LazyColumn {
                    items(subscriptionHistory.itemSnapshotList.items.size){position ->
                        SubscriptionHistoryItem(payment = subscriptionHistory.itemSnapshotList.items[position])
                    }

                    if (pagingUiState.value.isAppending) {
                        item {
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
                      item {
                          LoadStateAppendError(retry = {subscriptionHistory.retry()})
                      }
                    }
                }
            }
*/

            }
        }
    }
}