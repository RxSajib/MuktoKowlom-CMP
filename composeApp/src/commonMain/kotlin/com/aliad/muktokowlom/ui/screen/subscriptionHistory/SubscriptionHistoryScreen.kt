package com.aliad.muktokowlom.ui.screen.subscriptionHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.collectAsLazyPagingItems

import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.PaymentHistoryItem
import com.aliad.presentation.signIn.ui.subscriptionHistory.SubscriptionHistoryViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.subscription_history
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SubscriptionHistoryScreen(
    backStack: NavBackStack<NavKey>,
    rootBackStack: NavBackStack<NavKey>
) {

    val viewModel: SubscriptionHistoryViewModel = koinViewModel()
    val subscriptionHistory = viewModel.subscriptionHistory.collectAsLazyPagingItems()
    val selectedLan = viewModel.selectedLan.collectAsStateWithLifecycle("en")

    val pagingUiState = viewModel.pagingUiState.collectAsState()
    LaunchedEffect(subscriptionHistory.loadState) {
        viewModel.updatePagingLoadStates(
            loadStates = subscriptionHistory.loadState,
            itemCount = subscriptionHistory.itemCount
        )
    }

    LaunchedEffect(subscriptionHistory.loadState) {
        viewModel.updatePagingLoadStates(
            subscriptionHistory.loadState,
            subscriptionHistory.itemCount
        )
    }

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {


        Scaffold(
            topBar = {
                MyCustomAppBar(
                    onBackPress = {
                        try {
                            if (backStack.size > 1) {
                                backStack.removeLastOrNull()
                            } else {
                                rootBackStack.removeLastOrNull()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    },
                    title = stringResource(Res.string.subscription_history),
                    editProfile = {})
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(innerPadding).padding(horizontal = 16.dp)
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        subscriptionHistory.itemCount,
                        key = { index -> subscriptionHistory[index]?.id ?: index },
                        contentType = { index -> subscriptionHistory[index]?.cardType }) { position ->
                        PaymentHistoryItem(payment = subscriptionHistory[position], selectedBn = selectedLan.value)
                    }
                }

            }

        }
    }
}