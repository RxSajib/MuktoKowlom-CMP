package com.aliad.muktokowlom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.muktokowlom.ui.screen.category.CategoryScreen
import com.aliad.muktokowlom.ui.screen.homeScreen.HomeScreen
import com.aliad.muktokowlom.ui.screen.loginScreen.SignInScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.example.project.ui.screen.signupScreen.SignUpScreen

@Composable
fun AppNavigation() {

    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(AppDestination.SignInScreen::class, AppDestination.SignInScreen.serializer())
                subclass(AppDestination.SignUpScreen::class, AppDestination.SignUpScreen.serializer())
                subclass(AppDestination.HomeScreen::class, AppDestination.HomeScreen.serializer())
                subclass(AppDestination.CategoryScreen::class, AppDestination.CategoryScreen.serializer())
            }
        }
    }


    val element = arrayOf<AppDestination>(AppDestination.SignInScreen)
    val backStack = rememberNavBackStack(configuration = appConfig, elements = element)

    NavDisplay(
        backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppDestination.SignInScreen> {
                SignInScreen(backStack = backStack)
            }
            entry<AppDestination.SignUpScreen> {
                SignUpScreen(backStack = backStack)
            }
            entry<AppDestination.HomeScreen> {
                HomeScreen(backStack = backStack)
            }
            entry<AppDestination.CategoryScreen> {
                CategoryScreen(backStack = backStack)
            }
        },
    )
}