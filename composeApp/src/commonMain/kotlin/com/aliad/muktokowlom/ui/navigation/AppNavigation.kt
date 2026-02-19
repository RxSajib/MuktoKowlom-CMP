package com.aliad.muktokowlom.ui.navigation

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
import com.aliad.muktokowlom.ui.screen.category.CategoryScreen
import com.aliad.muktokowlom.ui.screen.category.CategoryWiseBook
import com.aliad.muktokowlom.ui.screen.component.MyCustomNotifySnackBar
import com.aliad.muktokowlom.ui.screen.earning_history.EarningHistoryScreen
import com.aliad.muktokowlom.ui.screen.editProfile.EditProfileScreen
import com.aliad.muktokowlom.ui.screen.homeScreen.HomeScreen
import com.aliad.muktokowlom.ui.screen.loginScreen.SignInScreen
import com.aliad.muktokowlom.ui.screen.otpView.OtpViewScreen
import com.aliad.muktokowlom.ui.screen.premium.PremiumScreen
import com.aliad.muktokowlom.ui.screen.privacy_policy.PrivacyPolicyScreen
import com.aliad.muktokowlom.ui.screen.profile.ProfileScreen
import com.aliad.muktokowlom.ui.screen.recoveryPassword.RecoveryPasswordScreen
import com.aliad.muktokowlom.ui.screen.splashScreen.SplashScreen
import com.aliad.muktokowlom.ui.screen.storyDetails.StoryDetailsScreen
import com.aliad.muktokowlom.ui.screen.storyType.StoryTypeScreen
import com.aliad.muktokowlom.ui.screen.subscription_history.SubscriptionHistoryScreen
import com.aliad.muktokowlom.ui.upload_stories.UploadStoriesScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.example.project.ui.screen.signupScreen.SignUpScreen

@Composable
fun AppNavigation() {

    val snackBarEvent = SnackBarEvent.state.collectAsState()

    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(AppDestination.SignInScreen::class, AppDestination.SignInScreen.serializer())
                subclass(AppDestination.SignUpScreen::class, AppDestination.SignUpScreen.serializer())
                subclass(AppDestination.HomeScreen::class, AppDestination.HomeScreen.serializer())
                subclass(AppDestination.CategoryScreen::class, AppDestination.CategoryScreen.serializer())
                subclass(AppDestination.CategoryWiseBook::class, AppDestination.CategoryWiseBook.serializer())
                subclass(AppDestination.StoryTypeWiseBook::class, AppDestination.StoryTypeWiseBook.serializer())
                subclass(AppDestination.StoryDetails::class, AppDestination.StoryDetails.serializer())
                subclass(AppDestination.Profile::class, AppDestination.Profile.serializer())
                subclass(AppDestination.EditProfile::class, AppDestination.EditProfile.serializer())
                subclass(AppDestination.Premium::class, AppDestination.Premium.serializer())
                subclass(AppDestination.PrivacyPolicy::class, AppDestination.PrivacyPolicy.serializer())
                subclass(AppDestination.EarningHistory::class, AppDestination.EarningHistory.serializer())
                subclass(AppDestination.UploadStories::class, AppDestination.UploadStories.serializer())
                subclass(AppDestination.SubscriptionHistory::class, AppDestination.SubscriptionHistory.serializer())
                subclass(AppDestination.SplashScreen::class, AppDestination.SplashScreen.serializer())
                subclass(AppDestination.RecoveryPassword::class, AppDestination.RecoveryPassword.serializer())
                subclass(AppDestination.OtpView::class, AppDestination.OtpView.serializer())
            }
        }
    }


    val element = arrayOf<AppDestination>(AppDestination.SplashScreen)
    val backStack = rememberNavBackStack(configuration = appConfig, elements = element)

    Box(modifier = Modifier.fillMaxSize()){
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
                entry<AppDestination.StoryDetails> { type ->
                    StoryDetailsScreen(myBookItem = type.myBookItem, backStack = backStack)
                }
                entry<AppDestination.Profile> {
                    ProfileScreen(backStack = backStack)
                }
                entry<AppDestination.EditProfile> {
                    EditProfileScreen(navBackStack = backStack)
                }
                entry<AppDestination.Premium> {
                    PremiumScreen(backStack = backStack)
                }
                entry<AppDestination.PrivacyPolicy> {
                    PrivacyPolicyScreen(backStack = backStack)
                }
                entry<AppDestination.EarningHistory> {
                    EarningHistoryScreen(backStack = backStack)
                }
                entry<AppDestination.UploadStories> {
                    UploadStoriesScreen(backStack = backStack)
                }
                entry<AppDestination.SubscriptionHistory> {
                    SubscriptionHistoryScreen(backStack = backStack)
                }
                entry<AppDestination.SplashScreen> {
                    SplashScreen(backStack = backStack)
                }
                entry<AppDestination.RecoveryPassword> {
                    RecoveryPasswordScreen(backStack = backStack)
                }
                entry<AppDestination.OtpView> {
                    OtpViewScreen(backStack = backStack, emailOrPhoneNumber = it.emailOrPhoneNumber)
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