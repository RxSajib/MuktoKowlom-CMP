package com.aliad.muktokowlom.ui.navigation.bottomAppBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Colors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.aliad.muktokowlom.ui.category.CategoryScreen
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.FavoriteStory.FavoriteStoryScreen
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.WidthGap
import com.aliad.muktokowlom.ui.screen.homeScreen.HomeScreen
import com.aliad.muktokowlom.ui.screen.profile.ProfileScreen
import com.aliad.muktokowlom.ui.screen.search.Search
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import com.sajib.data.appConstant.AppConstant
import io.github.alexzhirkevich.compottie.DotLottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.category
import muktokowlomcmp.composeapp.generated.resources.dashBoard
import muktokowlomcmp.composeapp.generated.resources.favorite
import muktokowlomcmp.composeapp.generated.resources.home
import muktokowlomcmp.composeapp.generated.resources.ic_fav_unselected
import muktokowlomcmp.composeapp.generated.resources.ic_filter
import muktokowlomcmp.composeapp.generated.resources.ic_home
import muktokowlomcmp.composeapp.generated.resources.ic_profile
import muktokowlomcmp.composeapp.generated.resources.icon_bookmark
import muktokowlomcmp.composeapp.generated.resources.icon_bookmark_selected
import muktokowlomcmp.composeapp.generated.resources.icon_category
import muktokowlomcmp.composeapp.generated.resources.icon_category_selected
import muktokowlomcmp.composeapp.generated.resources.icon_home
import muktokowlomcmp.composeapp.generated.resources.icon_home_selected
import muktokowlomcmp.composeapp.generated.resources.icon_search_selected
import muktokowlomcmp.composeapp.generated.resources.icon_setting
import muktokowlomcmp.composeapp.generated.resources.icon_setting_selected
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import muktokowlomcmp.composeapp.generated.resources.muktokowlom_web_link
import muktokowlomcmp.composeapp.generated.resources.profile
import muktokowlomcmp.composeapp.generated.resources.search
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BottomAppBarNavigation(rootBackStack: NavBackStack<NavKey>, sharedViewModel: SharedViewModel) {
    val appConfig = SavedStateConfiguration {
        this.serializersModule = SerializersModule {
            this.polymorphic(NavKey::class) {
                subclass(
                    AppDestination.BottomAppBar.DashBoard::class,
                    AppDestination.BottomAppBar.DashBoard.serializer()
                )
                subclass(
                    AppDestination.BottomAppBar.Category::class,
                    AppDestination.BottomAppBar.Category.serializer()
                )
                subclass(
                    AppDestination.BottomAppBar.Search::class,
                    AppDestination.BottomAppBar.Search.serializer()
                )
                subclass(
                    AppDestination.BottomAppBar.FavoriteStory::class,
                    AppDestination.BottomAppBar.FavoriteStory.serializer()
                )
                subclass(
                    AppDestination.BottomAppBar.Profile::class,
                    AppDestination.BottomAppBar.Profile.serializer()
                )
            }
        }
    }

    val dashBoardBackStack = rememberNavBackStack(appConfig, AppDestination.BottomAppBar.DashBoard)
    val categoryBackStack = rememberNavBackStack(appConfig, AppDestination.BottomAppBar.Category)
    val searchBackStack = rememberNavBackStack(appConfig, AppDestination.BottomAppBar.Search)
    val favoriteBackStack =
        rememberNavBackStack(appConfig, AppDestination.BottomAppBar.FavoriteStory)
    val profileBackStack = rememberNavBackStack(appConfig, AppDestination.BottomAppBar.Profile)


    val dataStoreViewModel: DataStoreViewModel = koinViewModel()
    val userName by dataStoreViewModel.getStringData(key = AppConstant.USER_NAME)
        .collectAsStateWithLifecycle("")
    val userEmailAddress by dataStoreViewModel.getStringData(key = AppConstant.USER_EMAIL_ADDRESS)
        .collectAsStateWithLifecycle("")
    val userProfileImage by dataStoreViewModel.getStringData(key = AppConstant.USER_PROFILE_IMAGE)
        .collectAsStateWithLifecycle(null)

    val AppDestinationSaver: Saver<AppDestination, String> = Saver(save = { destination ->
        Json.encodeToString(AppDestination.serializer(), destination)
    }, restore = { jsonString ->
        Json.decodeFromString(AppDestination.serializer(), jsonString)
    })

    var currentTab by rememberSaveable(
        stateSaver = AppDestinationSaver
    ) {
        mutableStateOf<AppDestination>(AppDestination.BottomAppBar.DashBoard)
    }
    // Select the active back stack
    val activeBackStack: NavBackStack<NavKey> = when (currentTab) {
        is AppDestination.BottomAppBar.DashBoard -> dashBoardBackStack
        is AppDestination.BottomAppBar.Category -> categoryBackStack
        is AppDestination.BottomAppBar.Search -> searchBackStack
        is AppDestination.BottomAppBar.FavoriteStory -> favoriteBackStack
        is AppDestination.BottomAppBar.Profile -> profileBackStack
        else -> dashBoardBackStack
    }

    val webLink = userEmailAddress.ifEmpty { stringResource(Res.string.muktokowlom_web_link) }

    val composition by rememberLottieComposition {
        LottieCompositionSpec.DotLottie(
            Res.readBytes("home_hover_pinch.json")
        )
    }
    val progress by animateLottieCompositionAsState(composition = composition)



    Scaffold(topBar = {
        MyCustomAppBar(
            isActonButtonEnable = true,
            isBackButtonEnable = false,
            title = stringResource(Res.string.muktokowlom),
            homeHeaderEnable = true,
            userProfileImage = userProfileImage,
            userName = userName.ifEmpty { stringResource(Res.string.muktokowlom) },
            userEmailAddress = webLink,
            onBackPress = {},
            editProfile = {

            }

        )

    }, bottomBar = {

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.inverseSurface,
        ) {

            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = onPrimaryLight,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    unselectedTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                ),
                selected = currentTab is AppDestination.BottomAppBar.DashBoard,
                onClick = { currentTab = AppDestination.BottomAppBar.DashBoard },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = if (currentTab is AppDestination.BottomAppBar.DashBoard) painterResource(
                                Res.drawable.icon_home_selected
                            ) else painterResource(Res.drawable.icon_home),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                        WidthGap(width = 2.dp)
                        Text(
                            stringResource(Res.string.home),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = adjustedFontSize(10f),
                            )
                        )
                    }

                },
                /* label = {

                 }*/
            )
            NavigationBarItem(
                selected = currentTab is AppDestination.BottomAppBar.Category,
                onClick = { currentTab = AppDestination.BottomAppBar.Category },
                colors = NavigationBarItemColors(
                    selectedIconColor = onPrimaryLight,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    unselectedTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = if (currentTab is AppDestination.BottomAppBar.Category) painterResource(
                                Res.drawable.icon_category_selected
                            ) else painterResource(Res.drawable.icon_category),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                        WidthGap(width = 2.dp)
                        Text(
                            text = stringResource(Res.string.category),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = adjustedFontSize(10f),
                            )
                        )
                    }

                },


                )

            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = onPrimaryLight,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    unselectedTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                ),
                selected = currentTab is AppDestination.BottomAppBar.Search,
                onClick = { currentTab = AppDestination.BottomAppBar.Search },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = if (currentTab is AppDestination.BottomAppBar.Search) painterResource(
                                Res.drawable.icon_search_selected
                            ) else
                                painterResource(Res.drawable.search_alt_svgrepo_com),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                        WidthGap(width = 2.dp)
                        Text(
                            stringResource(Res.string.search),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = adjustedFontSize(10f),
                            )
                        )
                    }

                },
                /*label = {

                }*/
            )
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = onPrimaryLight,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    unselectedTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                ),
                selected = currentTab is AppDestination.BottomAppBar.FavoriteStory,
                onClick = { currentTab = AppDestination.BottomAppBar.FavoriteStory },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = if (currentTab is AppDestination.BottomAppBar.FavoriteStory) painterResource(
                                Res.drawable.icon_bookmark_selected
                            ) else painterResource(Res.drawable.icon_bookmark),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                        WidthGap(width = 2.dp)
                        Text(
                            stringResource(Res.string.favorite),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = adjustedFontSize(10f),
                            )
                        )
                    }

                },
                /* label = {

                 }*/
            )
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = onPrimaryLight,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    unselectedTextColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                ),

                selected = currentTab is AppDestination.BottomAppBar.Profile,
                onClick = { currentTab = AppDestination.BottomAppBar.Profile },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = if (currentTab is AppDestination.BottomAppBar.Profile) painterResource(
                                Res.drawable.icon_setting_selected
                            ) else painterResource(Res.drawable.icon_setting),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            stringResource(Res.string.profile),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = adjustedFontSize(10f),
                            )
                        )
                    }

                },
                /*label = {

                }*/
            )
        }

    }) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = activeBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<AppDestination.BottomAppBar.DashBoard> {
                    HomeScreen(backStack = rootBackStack)
                }
                entry<AppDestination.BottomAppBar.Category> {
                    CategoryScreen(backStack = rootBackStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.BottomAppBar.Search> {
                    Search(backStack = rootBackStack, sharedViewModel = sharedViewModel)
                }
                entry<AppDestination.BottomAppBar.FavoriteStory> {
                    FavoriteStoryScreen(backStack = rootBackStack)
                }
                entry<AppDestination.BottomAppBar.Profile> {
                    ProfileScreen(backStack = rootBackStack, sharedViewModel = sharedViewModel)
                }
            })
    }
}