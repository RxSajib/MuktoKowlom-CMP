package com.aliad.muktokowlom.ui.screen.privacy_policy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.privacy_policy
import org.jetbrains.compose.resources.stringResource

@Composable
fun PrivacyPolicyScreen(backStack: NavBackStack<NavKey>) {
    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = stringResource(Res.string.privacy_policy),
                onBackPress = {
                    backStack.remove(AppDestination.PrivacyPolicy)
                },
                editProfile = {}
            )

        }
    ) {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){

        }
    }
}