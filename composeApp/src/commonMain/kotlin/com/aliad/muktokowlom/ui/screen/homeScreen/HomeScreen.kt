package com.aliad.muktokowlom.ui.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.LocalPlatformContext
import com.aliad.model.DashBord
import com.aliad.model.MyBookItem
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.HomeScreenShimmer
import com.aliad.muktokowlom.ui.component.HomeSeaBanner
import com.aliad.muktokowlom.ui.component.MyCustomBannerItem
import com.aliad.muktokowlom.ui.component.ServerError
import com.aliad.muktokowlom.ui.component.StoryCategoryWithAllButton
import com.aliad.muktokowlom.ui.component.StoryItemFixedSize
import com.aliad.muktokowlom.utils.MyCustomLogger
import com.aliad.presentation.signIn.ui.dashboard.DashBoardViewModel
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import com.aliad.presentation.utils.UiState
import com.lt.compose_views.banner.Banner
import com.lt.compose_views.banner.rememberBannerState
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import io.github.alexzhirkevich.compottie.Compottie.logger
import io.ktor.util.logging.Logger
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.all_release
import muktokowlomcmp.composeapp.generated.resources.most_popular
import muktokowlomcmp.composeapp.generated.resources.new_release
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


private const val TAG = "HomeScreen"
@Composable
fun HomeScreen(sharedViewModel: SharedViewModel, backStack: NavBackStack<NavKey>) {

    val dashBoardViewModel: DashBoardViewModel = koinViewModel()
    val dashBoardData = dashBoardViewModel.dashBoard.collectAsStateWithLifecycle()
    val selectedLan = dashBoardViewModel.selectedLan.collectAsStateWithLifecycle("en")
    val context = LocalPlatformContext.current

    rememberCoroutineScope()
    print("dashboard data ${dashBoardData.value}")
    stringResource(Res.string.most_popular)
    stringResource(Res.string.new_release)
    stringResource(Res.string.all_release)


    val refreshState = rememberRefreshLayoutState {
        setRefreshState(RefreshContentStateEnum.Refreshing)
            dashBoardViewModel.getDashBoardData()
    }

    LaunchedEffect(dashBoardViewModel.isLoading) {
        if (!dashBoardViewModel.isLoading) {
            refreshState.setRefreshState(RefreshContentStateEnum.Stop)
        }
    }

    Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {

        PullToRefresh(
            refreshLayoutState = refreshState,
        ) {

            when(dashBoardData.value){
                is UiState.Loading -> {
                    HomeScreenShimmer()
                }
                is UiState.Success -> {
                    val data = (dashBoardData.value as UiState.Success<DashBord>).data

                    Column(
                        modifier = Modifier.fillMaxSize()
                            .verticalScroll(state = rememberScrollState())
                    ) {


                        HomeSeaBanner {}

                        HeightGap(height = 10.dp)

                        StoryCategoryWithAllButton(
                            categoryTitle = stringResource(Res.string.most_popular), onClick = {
                                backStack.add(
                                    AppDestination.Dest(
                                        AppDestination.Dest.MostPopular::class.simpleName ?: ""
                                    )
                                )
                            })
                        Banner(
                            pageCount = data.lisOfPopularStories.size,
                            autoScrollTime = 5000L,
                            bannerState = rememberBannerState(),
                            orientation = Orientation.Horizontal,
                            bannerKey = { index -> data.lisOfPopularStories[index].toString() }) {

                            MyCustomBannerItem(
                                selectedLan = selectedLan.value,
                                context = context,
                                myBookItem = data.lisOfPopularStories[index]
                            ) { myBookItem ->
                                sharedViewModel.selectedBookID = myBookItem.storyID?: 0
                                backStack.add(
                                    AppDestination.Dest(
                                        AppDestination.Dest.StoryDetails::class.simpleName?: ""
                                    )
                                )
                            }
                        }

                        HeightGap(height = 10.dp)

                        StoryCategoryWithAllButton(
                            categoryTitle = stringResource(Res.string.new_release), onClick = {
                                backStack.add(
                                    AppDestination.Dest(
                                        AppDestination.Dest.NewReleaseStory::class.simpleName ?: ""
                                    )
                                )
                            })
                        LazyRow(state = rememberLazyListState()) {
                            items(
                                data.listOfNewReleaseStories,
                                key = {it.storyID?: it.hashCode()},
                                contentType = {it.category_name}
                            ) { bookItem ->
                                StoryItemFixedSize(selectLn = selectedLan.value, item = bookItem, context = context){myBookItem ->
                                    sharedViewModel.selectedBookID = myBookItem.storyID?: 0
                                    backStack.add(
                                        AppDestination.Dest(
                                            AppDestination.Dest.StoryDetails::class.simpleName?: ""
                                        )
                                    )
                                }
                            }
                        }
                        StoryCategoryWithAllButton(
                            categoryTitle = stringResource(Res.string.all_release), onClick = {
                                backStack.add(
                                    AppDestination.Dest(
                                        AppDestination.Dest.AllReleaseStory::class.simpleName ?: ""
                                    )
                                )
                            })
                        LazyRow(state = rememberLazyListState()) {
                            items(data.lifOfAllStories, key = {it.storyID?: it.hashCode()}, contentType = {it.category_name}) { bookItem ->
                                StoryItemFixedSize(selectLn = selectedLan.value, item = bookItem, context = context){myBookItem ->
                                    sharedViewModel.selectedBookID = myBookItem.storyID?: 0
                                    backStack.add(
                                        AppDestination.Dest(
                                            AppDestination.Dest.StoryDetails::class.simpleName?: ""
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                is UiState.Error -> {
                    ServerError {
                        dashBoardViewModel.getDashBoardData()
                    }
                }
            }
        }

    }
}