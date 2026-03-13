package com.aliad.muktokowlom.ui.screen.privacy_policy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import be.digitalia.compose.htmlconverter.htmlToString
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.privacy_policy
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PrivacyPolicyScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel: PrivacyPolicyViewModel = koinViewModel()
    val privacyPolicy = viewModel.privacyPolicy.collectAsStateWithLifecycle()
    val privacyPolicyDetails = htmlToString(privacyPolicy.value?.description ?: "")

    val refreshState = rememberRefreshLayoutState {
        this.setRefreshState(state = RefreshContentStateEnum.Refreshing)
        viewModel.getPrivacyPolicy()
    }
    LaunchedEffect(viewModel.isLoading) {
        if (!viewModel.isLoading) {
            refreshState.setRefreshState(state = RefreshContentStateEnum.Stop)
        }
    }

    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = stringResource(Res.string.privacy_policy),
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
                editProfile = {}
            )

        }
    ) { innerPadding ->

        PullToRefresh(refreshLayoutState = refreshState, modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()){
                Column(
                    modifier = Modifier.padding(innerPadding)
                        .verticalScroll(state = rememberScrollState()).padding(16.dp)
                ) {
                    Text(
                        text = privacyPolicyDetails,
                        modifier = Modifier.fillMaxSize(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

        }

    }
}