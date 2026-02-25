package com.aliad.muktokowlom.ui.navigation.bottomAppBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.unit.dp
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
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.homeScreen.HomeScreen
import com.aliad.muktokowlom.ui.screen.profile.ProfileScreen
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.dashBoard
import muktokowlomcmp.composeapp.generated.resources.favorite
import muktokowlomcmp.composeapp.generated.resources.ic_fav_unselected
import muktokowlomcmp.composeapp.generated.resources.ic_filter
import muktokowlomcmp.composeapp.generated.resources.ic_home
import muktokowlomcmp.composeapp.generated.resources.ic_profile
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import muktokowlomcmp.composeapp.generated.resources.profile
import muktokowlomcmp.composeapp.generated.resources.search
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomAppBarNavigation(rootBackStack: NavBackStack<NavKey>) {
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


    val AppDestinationSaver: Saver<AppDestination, String> = Saver(
        save = { destination ->
            Json.encodeToString(AppDestination.serializer(), destination)
        },
        restore = { jsonString ->
            Json.decodeFromString(AppDestination.serializer(), jsonString)
        }
    )

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


    Scaffold(
        topBar = {
            MyCustomAppBar(
                isActonButtonEnable = true,
                isBackButtonEnable = false,
                title = stringResource(Res.string.muktokowlom),
                homeHeaderEnable = true,
                userProfileImage = "https://img.freepik.com/free-photo/young-handsome-man-wearing-casual-tshirt-blue-background-happy-face-smiling-with-crossed-arms-looking-camera-positive-person_839833-12963.jpg?semt=ais_user_personalization&w=740&q=80",
                userName = "Sajib Roy",
                userEmailAddress = "Sajibroy206@gmail.com",
                onBackPress = {},
                editProfile = {

                })

        },
        bottomBar = {

            NavigationBar {
                NavigationBarItem(
                    selected = currentTab is AppDestination.BottomAppBar.DashBoard,
                    onClick = { currentTab = AppDestination.BottomAppBar.DashBoard },
                    icon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_home),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(Res.string.dashBoard)
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentTab is AppDestination.BottomAppBar.Category,
                    onClick = { currentTab = AppDestination.BottomAppBar.Category },
                    icon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_filter),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text("Category") }
                )

                NavigationBarItem(
                    selected = currentTab is AppDestination.BottomAppBar.Search,
                    onClick = { currentTab = AppDestination.BottomAppBar.Search },
                    icon = {
                        Icon(
                            painter = painterResource(Res.drawable.search_alt_svgrepo_com),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(stringResource(Res.string.search)) }
                )
                NavigationBarItem(
                    selected = currentTab is AppDestination.BottomAppBar.FavoriteStory,
                    onClick = { currentTab = AppDestination.BottomAppBar.FavoriteStory },
                    icon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_fav_unselected),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(stringResource(Res.string.favorite)) }
                )
                NavigationBarItem(
                    selected = currentTab is AppDestination.BottomAppBar.Profile,
                    onClick = { currentTab = AppDestination.BottomAppBar.Profile },
                    icon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_profile),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(stringResource(Res.string.profile)) }
                )

            }

        }
    ) { innerPadding ->
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
                    CategoryScreen(backStack = activeBackStack)
                }
                entry<AppDestination.BottomAppBar.Search> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Search",
                        )
                    }
                }
                entry<AppDestination.BottomAppBar.FavoriteStory> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "FavoriteStory",
                        )
                    }
                }
                entry<AppDestination.BottomAppBar.Profile> {
                    ProfileScreen(backStack = rootBackStack)
                }
            }
        )
    }
}