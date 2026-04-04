package com.aliad.muktokowlom.ui.screen.newRelease

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.new_release
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewReleaseScreen() {
    Surface(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)
    ) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = stringResource(Res.string.new_release), onBackPress = {},
                    editProfile = {}
                )
            }
        ) {innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

            }
        }
    }
}