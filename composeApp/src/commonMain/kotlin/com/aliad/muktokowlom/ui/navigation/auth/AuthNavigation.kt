package com.aliad.muktokowlom.ui.navigation.auth

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.loginScreen.SignInScreen
import com.aliad.muktokowlom.ui.screen.otpView.OtpViewScreen
import com.aliad.muktokowlom.ui.screen.recoveryPassword.RecoveryPasswordScreen
import com.aliad.muktokowlom.ui.screen.resetPassword.ResetPasswordScreen
import com.aliad.muktokowlom.ui.screen.updatePassword.UpdatePasswordScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.example.project.ui.screen.signupScreen.SignUpScreen

@Composable
fun AuthNavigation(startDest: AppDestination.Auth, rootBackStack: NavBackStack<NavKey>){

    val appConfig = SavedStateConfiguration {
        this.serializersModule = SerializersModule {
            this.polymorphic(NavKey::class){
                subclass(AppDestination.Auth.SignIn::class, AppDestination.Auth.SignIn.serializer())
                subclass(AppDestination.Auth.SignUp::class, AppDestination.Auth.SignUp.serializer())
                subclass(AppDestination.Auth.RecoveryPassword::class, AppDestination.Auth.RecoveryPassword.serializer())
                subclass(AppDestination.Auth.Otp::class, AppDestination.Auth.Otp.serializer())
                subclass(AppDestination.Auth.ResetPassword::class, AppDestination.Auth.ResetPassword.serializer())
            }
        }
    }

    val firstDest = when {
        startDest.firstDestName == AppDestination.Auth.SignIn::class.simpleName -> AppDestination.Auth.SignIn
        else ->  AppDestination.Auth.SignUp
    }

    val backStack = rememberNavBackStack(configuration = appConfig, firstDest)

    NavDisplay(
        backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppDestination.Auth.SignIn> {
                SignInScreen(backStack = backStack, rootBackStack = rootBackStack)
            }
            entry<AppDestination.Auth.SignUp> {
                SignUpScreen(backStack = backStack, rootBackStack = rootBackStack)
            }
            entry<AppDestination.Auth.RecoveryPassword> {
                RecoveryPasswordScreen(backStack = backStack)
            }
            entry<AppDestination.Auth.Otp> {
                OtpViewScreen(rootBackStack = rootBackStack, backStack = backStack, emailOrPhoneNumber = it.emailOrPhoneNumber)
            }
            entry<AppDestination.Dest.UpdatePassword> {data ->
                UpdatePasswordScreen(backStack = backStack, data = data)
            }
            entry<AppDestination.Auth.ResetPassword> {
                ResetPasswordScreen()
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