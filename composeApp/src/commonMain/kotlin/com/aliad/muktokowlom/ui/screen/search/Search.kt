package com.aliad.muktokowlom.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomInputFiled
import com.aliad.muktokowlom.ui.screen.component.SearchKeywordItem
import com.aliad.muktokowlom.ui.screen.component.StoryItemFixedSize
import com.aliad.muktokowlom.ui.screen.component.StoryShimmerRow
import com.aliad.presentation.signIn.ui.search.SearchViewModel
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import kotlinx.coroutines.delay
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.popular_searches
import muktokowlomcmp.composeapp.generated.resources.recent_searches
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_your_favourite_genre
import muktokowlomcmp.composeapp.generated.resources.your_have_no_search_history
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


data class BookSearch(
    val name: String
)

val bookSearchList = listOf(
    BookSearch("The Alchemist"),
    BookSearch("The Da Vinci Code"),
    BookSearch("The Alchemist"),
    BookSearch("Atomic Habits"),
    BookSearch("Rich Dad Poor Dad"),
    BookSearch("Think and Grow Rich"),
    BookSearch("The Power of Habit"),
    BookSearch("Start With Why"),
    BookSearch("The 7 Habits of Highly Effective People"),
    BookSearch("Deep Work"),
    BookSearch("Zero to One"),
    BookSearch("The Lean Startup"),
    BookSearch("Clean Code"),
    BookSearch("Kotlin in Action"),
    BookSearch("Android Programming Guide"),
    BookSearch("Effective Java"),
    BookSearch("Design Patterns"),
    BookSearch("Refactoring"),
    BookSearch("Head First Java"),
    BookSearch("Cracking the Coding Interview")
)


@Composable
fun Search(backStack: NavBackStack<NavKey>) {

    val viewModel: SearchViewModel = koinViewModel<SearchViewModel>()
    val popularSearchData = viewModel.popularSearch.collectAsStateWithLifecycle()

    val refreshLayoutState = rememberRefreshLayoutState {
        setRefreshState(state = RefreshContentStateEnum.Refreshing)
        viewModel.fetchPopularSearch()
    }
    LaunchedEffect(viewModel.isLoading) {
        if (!viewModel.isLoading) {
            refreshLayoutState.setRefreshState(state = RefreshContentStateEnum.Stop)
        }
    }


    PullToRefresh(refreshLayoutState = refreshLayoutState) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {


            Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(state = rememberScrollState())) {

                MyCustomInputFiled(
                    placeHolderText = stringResource(Res.string.search_your_favourite_genre),
                    text = viewModel.searchStoryData,
                    onValueChange = { firstNameInput ->
                        viewModel.searchStoryData = firstNameInput
                    },
                    isPasswordInput = false,
                    isVisiblePasswordChange = {},
                    isSearchEnable = true,
                    isPasswordVisibility = true,
                    leftIcon = painterResource(Res.drawable.search_alt_svgrepo_com),
                    onSearch = {
                        backStack.add(AppDestination.Dest(
                            AppDestination.Dest.SearchStoryResult::class.simpleName ?: ""
                        ))
                    }
                ) {}

                HeightGap(height = 10.dp)
                Text(
                    text = stringResource(Res.string.popular_searches),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                HeightGap(height = 10.dp)
                Row(modifier = Modifier.fillMaxWidth()) {
                    if(!viewModel.isLoading){
                        LazyRow {
                            items(popularSearchData.value.storyList.size){ position ->
                                StoryItemFixedSize(item = popularSearchData.value.storyList[position])
                            }
                        }
                    }else {
                        StoryShimmerRow()
                    }
                }
                HeightGap(height = 10.dp)
                Text(
                    text = stringResource(Res.string.recent_searches),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                HeightGap(height = 10.dp)

                Box(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(Res.drawable.search_alt_svgrepo_com),
                            modifier = Modifier.size(45.dp),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.inverseOnSurface)
                        )
                        HeightGap(height = 10.dp)
                        Text(
                            text = stringResource(Res.string.your_have_no_search_history),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.inverseOnSurface
                            )
                        )
                    }

                    FlowRow(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        repeat(bookSearchList.size) { nameOfSearchKey ->
                            SearchKeywordItem(keywordName = bookSearchList[nameOfSearchKey].name) {

                            }
                        }
                    }

                }
            }
        }
    }
}