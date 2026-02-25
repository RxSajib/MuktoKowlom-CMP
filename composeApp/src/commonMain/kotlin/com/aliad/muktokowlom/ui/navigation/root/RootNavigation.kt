package com.aliad.muktokowlom.ui.navigation.root

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.navigation.auth.AuthNavigation
import com.aliad.muktokowlom.ui.navigation.bottomAppBar.BottomAppBarNavigation
import com.aliad.muktokowlom.ui.navigation.dest.DestNavigation
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun RootNavigation (){

    val appConfig = SavedStateConfiguration {
        this.serializersModule = SerializersModule {
            this.polymorphic(NavKey::class){
                subclass(AppDestination.BottomAppBar::class, AppDestination.BottomAppBar.serializer())
                subclass(AppDestination.Dest::class, AppDestination.Dest.serializer())
            }
        }
    }

    val rootBackStack = rememberNavBackStack(configuration = appConfig, AppDestination.BottomAppBar)

    NavDisplay(
        backStack = rootBackStack,
        entryDecorators =  listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppDestination.BottomAppBar> {
                BottomAppBarNavigation(rootBackStack = rootBackStack)
            }
            entry<AppDestination.Dest> {dest ->
                DestNavigation(startDest = dest, rootBackStack = rootBackStack)
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
}