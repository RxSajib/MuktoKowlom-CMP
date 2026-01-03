package com.aliad.muktokowlom.ui.navigation

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
import com.aliad.muktokowlom.ui.screen.category.CategoryScreen
import com.aliad.muktokowlom.ui.screen.category.CategoryWiseBook
import com.aliad.muktokowlom.ui.screen.homeScreen.HomeScreen
import com.aliad.muktokowlom.ui.screen.loginScreen.SignInScreen
import com.aliad.muktokowlom.ui.screen.storyType.StoryTypeScreen
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
                subclass(AppDestination.CategoryWiseBook::class, AppDestination.CategoryWiseBook.serializer())
                subclass(AppDestination.StoryTypeWiseBook::class, AppDestination.StoryTypeWiseBook.serializer())
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
            entry<AppDestination.CategoryWiseBook> {category ->
                CategoryWiseBook(backStack = backStack, category = category)
            }
            entry<AppDestination.StoryTypeWiseBook> { type ->
                StoryTypeScreen(backStack = backStack, type = type)
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