package com.aliad.muktokowlom.ui.screen.earning_history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.earning_history
import org.jetbrains.compose.resources.stringResource

@Composable
fun EarningHistoryScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>){
    Scaffold(
        topBar = {
           MyCustomAppBar(onBackPress = {rootBackStack.removeLastOrNull()}, title = stringResource(
               Res.string.earning_history), editProfile = {})
        }
    ) {innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

        }
    }
}