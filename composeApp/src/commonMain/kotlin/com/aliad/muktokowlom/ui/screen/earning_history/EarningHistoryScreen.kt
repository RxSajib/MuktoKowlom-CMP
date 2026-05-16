package com.aliad.muktokowlom.ui.screen.earning_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.model.MyEarnHistory
import com.aliad.muktokowlom.ui.component.EarnHistoryItem
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.ServerError
import com.aliad.muktokowlom.ui.component.SubscriptionPlanItemShimmer
import com.aliad.presentation.signIn.ui.earningHistory.EarningHistoryViewModel
import com.aliad.presentation.utils.UiState
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.earning_history
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EarningHistoryScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {


    val viewModel: EarningHistoryViewModel = koinViewModel()
    val earningHistoryData = viewModel.earningHistoryData.collectAsStateWithLifecycle()
    val selectedLan = viewModel.selectedLan.collectAsStateWithLifecycle("en")

    val rememberRefreshLayoutState = rememberRefreshLayoutState {
        this.setRefreshState(state = RefreshContentStateEnum.Refreshing)
        viewModel.getEarningHistory()
    }


    LaunchedEffect(viewModel.loading) {
        if (!viewModel.loading) {
            rememberRefreshLayoutState.setRefreshState(state = RefreshContentStateEnum.Stop)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
    ) {

        Scaffold(
            topBar = {
                MyCustomAppBar(
                    onBackPress = { rootBackStack.removeLastOrNull() }, title = stringResource(
                        Res.string.earning_history
                    ), editProfile = {})
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface).padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                PullToRefresh(
                    refreshLayoutState = rememberRefreshLayoutState,
                ) {
                    when (earningHistoryData.value) {
                        is UiState.Loading -> {
                            Column(modifier = Modifier.fillMaxSize()) {
                                repeat(20) {
                                    SubscriptionPlanItemShimmer()
                                }
                            }
                        }

                        is UiState.Success -> {
                            val data =
                                (earningHistoryData.value as UiState.Success<List<MyEarnHistory>>).data
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                state = rememberLazyListState()
                            ) {
                                items(
                                    data,
                                    key = { it.id },
                                    contentType = { it.cardType }) { earningHistory ->
                                    EarnHistoryItem(
                                        myEarnHistory = earningHistory,
                                        selectedLan = selectedLan.value
                                    )
                                }
                            }
                        }

                        is UiState.Error -> {
                            ServerError {
                                viewModel.getEarningHistory()
                            }
                        }
                    }
                }
            }
        }

    }
}