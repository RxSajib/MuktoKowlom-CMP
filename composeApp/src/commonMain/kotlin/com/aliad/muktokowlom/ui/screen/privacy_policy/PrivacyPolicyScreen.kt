package com.aliad.muktokowlom.ui.screen.privacy_policy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import be.digitalia.compose.htmlconverter.htmlToString
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.utils.getTitle
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.privacy_policy.PrivacyPolicyViewModel
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import io.github.rhobus.kloading.animation.WatchRunningAnimation
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.privacy_policy
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PrivacyPolicyScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val privacyPolicyViewModel: PrivacyPolicyViewModel = koinViewModel()
    val privacyPolicy = privacyPolicyViewModel.privacyPolicy.collectAsStateWithLifecycle()

    val viewModel : DataStoreViewModel = koinViewModel()
    val selectLn = viewModel.getStringData(key = AppConstant.SELECT_LOCAL).collectAsStateWithLifecycle("en")

    val privacyPolicyDetailsEn = htmlToString(privacyPolicy.value?.description ?: "")
    val privacyPolicyDetailsBn = htmlToString(privacyPolicy.value?.descriptionBn ?: "")


    val refreshState = rememberRefreshLayoutState {
        this.setRefreshState(state = RefreshContentStateEnum.Refreshing)
        privacyPolicyViewModel.getPrivacyPolicy()
    }
    LaunchedEffect(privacyPolicyViewModel.isLoading) {
        if (!privacyPolicyViewModel.isLoading) {
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
        Surface(
            modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
        ) {

            PullToRefresh(
                refreshLayoutState = refreshState,
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                    if(privacyPolicyViewModel.isLoading){
                        WatchRunningAnimation(
                            clockColor = Color.Gray.copy(alpha = 0.1f),
                            handColor = Color.Gray,
                            clockSize = 30.dp
                        )
                    }else {
                        Column(
                            modifier = Modifier.fillMaxSize()
                                .verticalScroll(state = rememberScrollState()).padding(16.dp)
                        ) {
                            Text(
                                text = getTitle(selectLn = selectLn.value, title = privacyPolicyDetailsEn, titleBn = privacyPolicyDetailsBn),
                                modifier = Modifier.fillMaxSize(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }


                }
            }
        }

    }
}