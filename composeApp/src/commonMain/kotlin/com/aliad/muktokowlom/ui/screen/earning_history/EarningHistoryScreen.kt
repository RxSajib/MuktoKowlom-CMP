package com.aliad.muktokowlom.ui.screen.earning_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.presentation.signIn.ui.earningHistory.EarningHistoryViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.earning_history
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EarningHistoryScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>){


    val viewModel : EarningHistoryViewModel = koinViewModel()
    val earningHistoryData = viewModel.getEarningHistory.collectAsStateWithLifecycle(emptyList())

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {

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
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(earningHistoryData.value){earningHistory ->
                        Text(
                            text = earningHistory.amount
                        )
                    }
                }
            }
        }

    }
}