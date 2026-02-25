package com.aliad.muktokowlom.ui.upload_stories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.upload_stories
import org.jetbrains.compose.resources.stringResource

@Composable
fun UploadStoriesScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {
    Scaffold(
        topBar = {
            MyCustomAppBar(
                onBackPress = {
                    try {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }else {
                            rootBackStack.removeLastOrNull()
                        }
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                },
                title = stringResource(Res.string.upload_stories),
                editProfile = {})
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

        }
    }
}