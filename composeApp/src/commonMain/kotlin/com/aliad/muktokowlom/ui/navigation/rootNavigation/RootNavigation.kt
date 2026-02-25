package com.aliad.muktokowlom.ui.navigation.rootNavigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.navigation.bottomAppBarNavigation.BottomAppBarNavigation
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun RootNavigation (){

    val appConfig = SavedStateConfiguration {
        this.serializersModule = SerializersModule {
            this.polymorphic(NavKey::class){
                subclass(AppDestination.BottomAppBar::class, AppDestination.BottomAppBar.serializer())
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
        }
    )
}