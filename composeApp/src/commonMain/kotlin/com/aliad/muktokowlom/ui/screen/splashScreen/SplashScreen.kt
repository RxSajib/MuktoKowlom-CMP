package com.aliad.muktokowlom.ui.screen.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.delay
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(backStack: NavBackStack<NavKey>){

    val viewModel : DataStoreViewModel = koinViewModel()


    LaunchedEffect(Unit){
        delay(2500L)
        viewModel.getStringData(key = AppConstant.ACCESS_TOKEN).collect {accessToken ->
            if (accessToken.isEmpty()){
                backStack.clear()
                backStack.add(AppDestination.SignInScreen)
            }else {
                backStack.clear()
                backStack.add(AppDestination.HomeScreen)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(Res.drawable.muktokowlom),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
    }
}

