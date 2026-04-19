package com.aliad.muktokowlom.ui.navigation.root

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.helper.SnackBarEvent
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.navigation.auth.AuthNavigation
import com.aliad.muktokowlom.ui.navigation.bottomAppBar.BottomAppBarNavigation
import com.aliad.muktokowlom.ui.navigation.dest.DestNavigation
import com.aliad.muktokowlom.ui.screen.component.MyCustomNotifySnackBar
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RootNavigation() {

    val snackBarEvent = SnackBarEvent.state.collectAsState()
    val sharedViewModel : SharedViewModel = koinViewModel()

    val appConfig = SavedStateConfiguration {
        this.serializersModule = SerializersModule {
            this.polymorphic(NavKey::class) {
                subclass(
                    AppDestination.BottomAppBar::class,
                    AppDestination.BottomAppBar.serializer()
                )
                subclass(AppDestination.Dest::class, AppDestination.Dest.serializer())
            }
        }
    }

    val rootBackStack = rememberNavBackStack(configuration = appConfig, AppDestination.BottomAppBar)

    Box(modifier = Modifier.fillMaxSize()) {

        NavDisplay(
            backStack = rootBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<AppDestination.BottomAppBar> {
                    BottomAppBarNavigation(rootBackStack = rootBackStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.Dest> { dest ->
                    DestNavigation(startDest = dest, rootBackStack = rootBackStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.Auth> {
                    AuthNavigation(startDest = it, rootBackStack = rootBackStack)
                }
            },

            transitionSpec = {
                slideInHorizontally(initialOffsetX = { it }) togetherWith
                        slideOutHorizontally(targetOffsetX = { -it })
            },
            popTransitionSpec = {
                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                        slideOutHorizontally(targetOffsetX = { it })
            },
            predictivePopTransitionSpec = {
                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                        slideOutHorizontally(targetOffsetX = { it })
            },

            )

        if (snackBarEvent.value.show) {

            MyCustomNotifySnackBar(
                modifier = Modifier.fillMaxSize(),
                message = snackBarEvent.value.details ?: "",
                //     leftIcon = snackBarEvent.value.leftIcon, //  painterResource(snackBarEvent.value.leftIcon ?: Res.drawable.muktokowlom),
                onDismiss = {
                    SnackBarEvent.save(
                        details = snackBarEvent.value.copy(
                            show = false,
                            details = null,
                            title = null
                        )
                    )
                })
        }

    }

}