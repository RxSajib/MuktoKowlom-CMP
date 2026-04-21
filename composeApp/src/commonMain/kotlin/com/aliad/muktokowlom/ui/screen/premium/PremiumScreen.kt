package com.aliad.muktokowlom.ui.screen.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.SubscriptionPlanItem
import com.aliad.muktokowlom.ui.screen.component.SubscriptionPlanItemShimmer
import com.aliad.muktokowlom.ui.screen.component.WidthGap
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.subscriptionPlan.SubscriptionPlanViewModel
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.amount
import muktokowlomcmp.composeapp.generated.resources.buy
import muktokowlomcmp.composeapp.generated.resources.premium
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PremiumScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel : SubscriptionPlanViewModel = koinViewModel()
    val list = viewModel.premiumPlanStateFlow.collectAsStateWithLifecycle()

    val dataStoreViewModel : DataStoreViewModel = koinViewModel()
    val selectLn = dataStoreViewModel.getStringData(key = AppConstant.SELECT_LOCAL).collectAsStateWithLifecycle("en")

    val rememberRefreshLayoutState = rememberRefreshLayoutState {
        this.setRefreshState(state = RefreshContentStateEnum.Refreshing)
        viewModel.getPremiumPlanList()
    }

    LaunchedEffect(viewModel.loading){
        if(!viewModel.loading){
            rememberRefreshLayoutState.setRefreshState(state = RefreshContentStateEnum.Stop)
        }
    }

    Scaffold(
        topBar = {
            MyCustomAppBar(title = stringResource(Res.string.premium), onBackPress = {
                try {
                    if (backStack.size > 1) {
                        backStack.removeLastOrNull()
                    }else {
                        rootBackStack.removeLastOrNull()
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }

            }, editProfile = {})
        }
    ) { innerPadding ->

        PullToRefresh(refreshLayoutState = rememberRefreshLayoutState, modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(list.value) { subscription ->
                        SubscriptionPlanItem(
                            selected = viewModel.selectedSubscriptionIndex == (subscription.id
                                ?: 0), subscription = subscription,
                            selectLn = selectLn,
                        ) {
                            viewModel.selectedSubscriptionIndex = subscription.id ?: 0
                            viewModel.selectedPackage = subscription

                        }
                    }
                }
            }

            if (viewModel.loading) {
                Column(modifier = Modifier.fillMaxSize()) {
                    repeat(20) {
                        SubscriptionPlanItemShimmer()
                    }
                }
            } else {
                if (list.value.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(Res.string.amount),
                                style = MaterialTheme.typography.bodySmall
                            )
                            WidthGap(width = 5.dp)
                            Text(
                                text = "৳",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            WidthGap(width = 1.dp)
                            Text(
                                text = if(selectLn.value == "en") viewModel.selectedPackage.price ?: "0" else AppConstant.toBanglaDigits(viewModel.selectedPackage.price ?: "0") ,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.W500
                                )
                            )
                        }

                        MyCustomButton(
                            isEnable = true,
                            onClickButton = {},
                            title = stringResource(Res.string.buy),
                            modifier = Modifier.weight(1f),
                            padding = 0.dp
                        )
                    }
                }
            }
        }

        }
    }
}