package com.aliad.muktokowlom.ui.navigation.dest

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.muktokowlom.ui.category.CategoryScreen
import com.aliad.muktokowlom.ui.category.CategoryWiseBook
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.allCategory.AllCategoryScreen
import com.aliad.muktokowlom.ui.screen.allRelease.AllStoryScreen
import com.aliad.muktokowlom.ui.screen.changeLaunguage.ChangeLanguageScreen
import com.aliad.muktokowlom.ui.screen.earning_history.EarningHistoryScreen
import com.aliad.muktokowlom.ui.screen.editProfile.EditProfileScreen
import com.aliad.muktokowlom.ui.screen.homeScreen.HomeScreen
import com.aliad.muktokowlom.ui.screen.mosPopulatStory.MostPopularStoryScreen
import com.aliad.muktokowlom.ui.screen.newRelease.NewReleaseScreen
import com.aliad.muktokowlom.ui.screen.premium.PremiumScreen
import com.aliad.muktokowlom.ui.screen.privacy_policy.PrivacyPolicyScreen
import com.aliad.muktokowlom.ui.screen.profile.ProfileScreen
import com.aliad.muktokowlom.ui.screen.publishedPendingStoryList.PublishedPendingStoryListScreen
import com.aliad.muktokowlom.ui.screen.searchStory.SearchStoryResultScreen
import com.aliad.muktokowlom.ui.screen.storyDetails.StoryDetailsScreen
import com.aliad.muktokowlom.ui.screen.storyType.StoryTypeScreen
import com.aliad.muktokowlom.ui.screen.storyView.StoryViewScreen
import com.aliad.muktokowlom.ui.screen.subscriptionHistory.SubscriptionHistoryScreen
import com.aliad.muktokowlom.ui.screen.updatePassword.UpdatePasswordScreen
import com.aliad.muktokowlom.ui.upload_stories.UploadStoriesScreen
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun DestNavigation(
    startDest: AppDestination.Dest,
    rootBackStack: NavBackStack<NavKey>,
    sharedViewModel: SharedViewModel
) {


    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                //  subclass(AppDestination.HomeScreen::class, AppDestination.HomeScreen.serializer())
                  subclass(AppDestination.Dest.MostPopular::class, AppDestination.Dest.MostPopular.serializer())
                subclass(
                    AppDestination.Dest.CategoryWiseBook::class,
                    AppDestination.Dest.CategoryWiseBook.serializer()
                )
                subclass(
                    AppDestination.Dest.StoryTypeWiseBook::class,
                    AppDestination.Dest.StoryTypeWiseBook.serializer()
                )
                subclass(
                    AppDestination.Dest.StoryView::class,
                    AppDestination.Dest.StoryView.serializer()
                )
                subclass(
                    AppDestination.Dest.SearchStoryResult::class,
                    AppDestination.Dest.SearchStoryResult.serializer()
                )
                subclass(
                    AppDestination.Dest.EditProfile::class,
                    AppDestination.Dest.EditProfile.serializer()
                )
                subclass(
                    AppDestination.Dest.Premium::class,
                    AppDestination.Dest.Premium.serializer()
                )
                subclass(
                    AppDestination.Dest.PrivacyPolicy::class,
                    AppDestination.Dest.PrivacyPolicy.serializer()
                )
                subclass(
                    AppDestination.Dest.EarningHistory::class,
                    AppDestination.Dest.EarningHistory.serializer()
                )
                subclass(
                    AppDestination.Dest.UploadStories::class,
                    AppDestination.Dest.UploadStories.serializer()
                )
                subclass(
                    AppDestination.Dest.SubscriptionHistory::class,
                    AppDestination.Dest.SubscriptionHistory.serializer()
                )
                subclass(
                    AppDestination.Dest.UpdatePassword::class,
                    AppDestination.Dest.UpdatePassword.serializer()
                )
                subclass(
                    AppDestination.Dest.PublishedPendingStory::class,
                    AppDestination.Dest.PublishedPendingStory.serializer()
                )
                subclass(
                    AppDestination.Dest.AllReleaseStory::class,
                    AppDestination.Dest.AllReleaseStory.serializer()
                )
                subclass(
                    AppDestination.Dest.NewReleaseStory::class,
                    AppDestination.Dest.NewReleaseStory.serializer()
                )

                subclass(
                    AppDestination.Dest.AllCategory::class,
                    AppDestination.Dest.AllCategory.serializer()
                )

                subclass(
                    AppDestination.Dest.ChangeLanguage::class,
                    AppDestination.Dest.ChangeLanguage.serializer()
                )
            }
        }
    }


    val firstDest = when {
        startDest.firstDestName == AppDestination.Dest.EditProfile::class.simpleName -> AppDestination.Dest.EditProfile
        startDest.firstDestName == AppDestination.Dest.EarningHistory::class.simpleName -> AppDestination.Dest.EarningHistory
        startDest.firstDestName == AppDestination.Dest.CategoryWiseBook::class.simpleName -> AppDestination.Dest.CategoryWiseBook
        startDest.firstDestName == AppDestination.Dest.PrivacyPolicy::class.simpleName -> AppDestination.Dest.PrivacyPolicy
        startDest.firstDestName == AppDestination.Dest.Premium::class.simpleName -> AppDestination.Dest.Premium
        startDest.firstDestName == AppDestination.Dest.UploadStories::class.simpleName -> AppDestination.Dest.UploadStories
        startDest.firstDestName == AppDestination.Dest.SubscriptionHistory::class.simpleName -> AppDestination.Dest.SubscriptionHistory
        startDest.firstDestName == AppDestination.Dest.StoryTypeWiseBook::class.simpleName -> AppDestination.Dest.StoryTypeWiseBook(
            typeName = ""
        )

        startDest.firstDestName == AppDestination.Dest.SearchStoryResult::class.simpleName -> AppDestination.Dest.SearchStoryResult
        startDest.firstDestName == AppDestination.Dest.PublishedPendingStory::class.simpleName -> AppDestination.Dest.PublishedPendingStory
        startDest.firstDestName == AppDestination.Dest.AllReleaseStory::class.simpleName -> AppDestination.Dest.AllReleaseStory
        startDest.firstDestName == AppDestination.Dest.NewReleaseStory::class.simpleName -> AppDestination.Dest.NewReleaseStory
        startDest.firstDestName == AppDestination.Dest.MostPopular::class.simpleName -> AppDestination.Dest.MostPopular
        startDest.firstDestName == AppDestination.Dest.ChangeLanguage::class.simpleName -> AppDestination.Dest.ChangeLanguage
        else -> throw Exception("Invalid destination")
    }


    val backStack = rememberNavBackStack(appConfig, firstDest)

    Box(modifier = Modifier.fillMaxSize()) {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {

                entry<AppDestination.HomeScreen> {
                    HomeScreen(backStack = backStack)
                }
                entry<AppDestination.CategoryScreen> {
                    CategoryScreen(backStack = backStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.Dest.CategoryWiseBook> {
                    CategoryWiseBook(backStack = backStack, rootBackStack = rootBackStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.Dest.StoryTypeWiseBook> { type ->
                    StoryTypeScreen(
                        backStack = backStack,
                        rootBackStack = rootBackStack,
                        type = type
                    )
                }
                entry<AppDestination.StoryDetails> { type ->
                    StoryDetailsScreen(myBookItem = type.myBookItem, backStack = backStack)
                }
                entry<AppDestination.Profile> {
                    ProfileScreen(backStack = backStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.Dest.EditProfile> {
                    EditProfileScreen(navBackStack = backStack, rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.Premium> {
                    PremiumScreen(backStack = backStack, rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.PrivacyPolicy> {
                    PrivacyPolicyScreen(backStack = backStack, rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.EarningHistory> {
                    EarningHistoryScreen(backStack = backStack, rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.UploadStories> {
                    UploadStoriesScreen(backStack = backStack, rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.SubscriptionHistory> {
                    SubscriptionHistoryScreen(backStack = backStack, rootBackStack = rootBackStack)
                }

                entry<AppDestination.Dest.UpdatePassword> {data ->
                    UpdatePasswordScreen(backStack = backStack, data = data)
                }
                entry<AppDestination.Dest.StoryView> {
                    StoryViewScreen(backStack = backStack)
                }
                entry<AppDestination.Dest.SearchStoryResult> {
                    SearchStoryResultScreen(backStack = backStack, rootBackStack = rootBackStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.Dest.PublishedPendingStory> {
                    PublishedPendingStoryListScreen(
                        backStack = backStack,
                        rootBackStack = rootBackStack,
                        viewModel = sharedViewModel
                    )
                }
                entry<AppDestination.Dest.AllReleaseStory> {
                    AllStoryScreen(backStack = backStack,
                        rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.NewReleaseStory> {
                    NewReleaseScreen(backStack = backStack,
                        rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.MostPopular> {
                    MostPopularStoryScreen(backStack = backStack,
                        rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.AllCategory> {
                    AllCategoryScreen(backStack = backStack,
                        rootBackStack = rootBackStack,
                        sharedViewModel = sharedViewModel)
                }

                entry<AppDestination.Dest.ChangeLanguage> {
                    ChangeLanguageScreen(
                        backStack = backStack,
                        rootBackStack = rootBackStack,
                    )
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


}