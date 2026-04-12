package com.aliad.muktokowlom.ui.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.model.MyBookItem
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.HomeScreenShimmer
import com.aliad.muktokowlom.ui.screen.component.HomeSeaBanner
import com.aliad.muktokowlom.ui.screen.component.MyCustomBannerItem
import com.aliad.muktokowlom.ui.screen.component.StoryCategoryWithAllButton
import com.aliad.muktokowlom.ui.screen.component.StoryItemFixedSize
import com.aliad.presentation.signIn.ui.dashboard.DashBoardViewModel
import com.lt.compose_views.banner.Banner
import com.lt.compose_views.banner.rememberBannerState
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.all_release
import muktokowlomcmp.composeapp.generated.resources.most_popular
import muktokowlomcmp.composeapp.generated.resources.new_release
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen(backStack: NavBackStack<NavKey>) {

    val dashBoardViewModel: DashBoardViewModel = koinViewModel()
    val dashBoardData = dashBoardViewModel.dashBoard.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    print("dashboard data ${dashBoardData.value}")
    val mostPopularStory = stringResource(Res.string.most_popular)
    val newReleaseStory = stringResource(Res.string.new_release)
    val allStory = stringResource(Res.string.all_release)


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

            Box(modifier = Modifier.fillMaxSize()) {

                if (dashBoardViewModel.isLoading) {
                    HomeScreenShimmer()
                } else {

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
                            pageCount = dashBoardData.value?.lisOfPopularStories?.size ?: 0,
                            autoScrollTime = 5000L,
                            bannerState = rememberBannerState(),
                            orientation = Orientation.Horizontal,
                            bannerKey = { index -> dashBoardData.value?.lisOfPopularStories[index].toString() }) {

                            MyCustomBannerItem(
                                myBookItem = dashBoardData.value?.lisOfPopularStories[index]
                                    ?: MyBookItem()
                            ) { myBookItem ->
                                print("my book item $myBookItem")
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
                        LazyRow {
                            items(
                                dashBoardData.value?.listOfNewReleaseStories ?: emptyList()
                            ) { bookItem ->
                                StoryItemFixedSize(item = bookItem)
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
                        LazyRow {
                            items(dashBoardData.value?.lifOfAllStories ?: emptyList()) { bookItem ->
                                StoryItemFixedSize(item = bookItem)
                            }
                        }
                    }
                }
            }
        }

    }
}