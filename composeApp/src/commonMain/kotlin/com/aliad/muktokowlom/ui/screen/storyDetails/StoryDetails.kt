package com.aliad.muktokowlom.ui.screen.storyDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import be.digitalia.compose.htmlconverter.htmlToString
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.aliad.helper.SnackBarEvent
import com.aliad.model.MyBookItem
import com.aliad.model.SnackBarDetails
import com.aliad.muktokowlom.ui.bottomSheet.RatingBottomSheet
import com.aliad.muktokowlom.ui.component.EmptyBookDetails
import com.aliad.muktokowlom.ui.component.EmptySimilarStory
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.LikeStoryItem
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.MyCustomButton
import com.aliad.muktokowlom.ui.component.QuickAccessButton
import com.aliad.muktokowlom.ui.component.ServerError
import com.aliad.muktokowlom.ui.component.WidthGap
import com.aliad.muktokowlom.ui.component.WriterInfo
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryDark
import com.aliad.muktokowlom.utils.AppHelper.toUSFormatWithMonth
import com.aliad.muktokowlom.utils.getStoryData
import com.aliad.presentation.signIn.ui.sharedViewModel.SharedViewModel
import com.aliad.presentation.signIn.ui.storyDetails.StoryDetailsViewModel
import com.aliad.presentation.utils.UiState
import com.eygraber.seymour.SeymourText
import com.lt.compose_views.refresh_layout.PullToRefresh
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import io.github.rhobus.kloading.animation.WatchRunningAnimation
import kotlinx.datetime.LocalDate
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.about_book
import muktokowlomcmp.composeapp.generated.resources.add_favorites
import muktokowlomcmp.composeapp.generated.resources.all_release
import muktokowlomcmp.composeapp.generated.resources.book_svgrepo_com_icon
import muktokowlomcmp.composeapp.generated.resources.category
import muktokowlomcmp.composeapp.generated.resources.comment_posted_successfully
import muktokowlomcmp.composeapp.generated.resources.dashboard
import muktokowlomcmp.composeapp.generated.resources.favorite_disable
import muktokowlomcmp.composeapp.generated.resources.icon_category
import muktokowlomcmp.composeapp.generated.resources.icon_read_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.icon_star_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.no_similar_story
import muktokowlomcmp.composeapp.generated.resources.placeholder
import muktokowlomcmp.composeapp.generated.resources.published
import muktokowlomcmp.composeapp.generated.resources.rating
import muktokowlomcmp.composeapp.generated.resources.read_full_story
import muktokowlomcmp.composeapp.generated.resources.see_less
import muktokowlomcmp.composeapp.generated.resources.see_more
import muktokowlomcmp.composeapp.generated.resources.send_rating
import muktokowlomcmp.composeapp.generated.resources.similar_story
import muktokowlomcmp.composeapp.generated.resources.views
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun StoryDetailsScreen(
    sharedViewModel: SharedViewModel,
    backStack: NavBackStack<NavKey>,
    rootBackStack: NavBackStack<NavKey>
) {

    val viewModel: StoryDetailsViewModel = koinViewModel(
        parameters = {
            parametersOf(sharedViewModel.selectedBookID.toString())
        }
    )
    val contextCoil = LocalPlatformContext.current
    val storyData = viewModel.storyData.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current
    val successMessage = stringResource(Res.string.comment_posted_successfully)


    val refreshState = rememberRefreshLayoutState {
        setRefreshState(RefreshContentStateEnum.Refreshing)
        viewModel.getStoryDetails()
    }
    LaunchedEffect(viewModel.isLoading) {
        if (!viewModel.isLoading) {
            refreshState.setRefreshState(RefreshContentStateEnum.Stop)
        }
    }

    LaunchedEffect(lifecycle.lifecycle) {
        lifecycle.lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.ratingAndFeedbackData.collect { commentData ->
                commentData.status?.let { isSuccess ->
                    if (isSuccess) {
                        viewModel.isOpenRatingBottomSheet = false
                        SnackBarEvent.save(
                            details = SnackBarDetails(
                                details = successMessage,
                                show = true,
                                leftIcon = Icons.Default.LockOpen
                            )
                        )
                    } else {
                        SnackBarEvent.save(
                            details = SnackBarDetails(
                                details = commentData.message_en,
                                show = true,
                                leftIcon = Icons.Default.LockOpen
                            )
                        )
                    }
                }
            }
        }
    }



    Surface(
        modifier = Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.surface
        )
    ) {

        Scaffold(
            topBar = {
                MyCustomAppBar(title = "", onBackPress = {
                    try {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        } else {
                            rootBackStack.removeLastOrNull()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }, editProfile = {})
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding).background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                PullToRefresh(
                    refreshLayoutState = refreshState,
                    modifier = Modifier.fillMaxSize()
                ) {

                when (storyData.value) {
                    is UiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            WatchRunningAnimation(
                                clockColor = Color.Gray.copy(alpha = 0.1f),
                                handColor = Color.Gray,
                                clockSize = 30.dp
                            )
                        }
                    }

                    is UiState.Success -> {
                        val data = (storyData.value as UiState.Success<MyBookItem>).data
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier.fillMaxSize()
                                    .verticalScroll(state = rememberScrollState()),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                HeightGap(height = 15.dp)
                                AsyncImage(
                                    model = ImageRequest.Builder(contextCoil).data(data.completedImageUri).size(500).build(),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = null,
                                    placeholder = painterResource(Res.drawable.placeholder),
                                    error = painterResource(Res.drawable.placeholder),
                                    modifier = Modifier.fillMaxWidth(.5f).aspectRatio(2f)
                                        .clip(shape = RoundedCornerShape(10.dp))
                                )
                                HeightGap(height = 15.dp)
                                Text(
                                    text = data.titleBn ?: " Unknown Title",
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )

                                )
                                HeightGap(height = 10.dp)
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    MyCustomButton(
                                        title = stringResource(Res.string.send_rating),
                                        modifier = Modifier.weight(1f),
                                        leftIcon = painterResource(Res.drawable.icon_star_svgrepo_com),
                                        backgroundColor = MaterialTheme.colorScheme.primary.copy(
                                            alpha = 0.8f
                                        ),
                                        onClickButton = {
                                            viewModel.isOpenRatingBottomSheet = true
                                        },
                                        padding = 0.dp
                                    )
                                    WidthGap(width = 20.dp)
                                    MyCustomButton(
                                        title = stringResource(Res.string.read_full_story),
                                        modifier = Modifier.weight(1f),
                                        leftIcon = painterResource(Res.drawable.icon_read_svgrepo_com),
                                        onClickButton = {
                                            backStack.add(AppDestination.Dest.StoryView)
                                        },
                                        padding = 0.dp
                                    )
                                }
                                HeightGap(height = 10.dp)
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(
                                        modifier = Modifier.weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        data.publishDate?.let {
                                            Text(
                                                text = LocalDate.parse(it).toUSFormatWithMonth(),
                                                style = MaterialTheme.typography.titleSmall.copy(
                                                    fontWeight = FontWeight.W500,
                                                    color = MaterialTheme.colorScheme.primary,
                                                    fontSize = adjustedFontSize(10f)
                                                ),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }

                                        Text(
                                            text = stringResource(Res.string.published),
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.W400,
                                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                                fontSize = adjustedFontSize(8f)
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }

                                    Column(
                                        modifier = Modifier.weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = data.views ?: "0",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.W500,
                                                color = MaterialTheme.colorScheme.primary,
                                                fontSize = adjustedFontSize(10f)
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = stringResource(Res.string.views),
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.W400,
                                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                                fontSize = adjustedFontSize(8f)
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }

                                    Column(
                                        modifier = Modifier.weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = data.rating ?: "0.0",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.W500,
                                                color = MaterialTheme.colorScheme.primary,
                                                fontSize = adjustedFontSize(10f)
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = stringResource(Res.string.rating),
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.W400,
                                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                                fontSize = adjustedFontSize(8f)
                                            ),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                HeightGap(height = 10.dp)
                                WriterInfo(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    writerName = data.user?.name ?: "Unknown",
                                    profileImage = data.user?.profileImage ?: "",
                                    context = contextCoil
                                ) {

                                }
                                HeightGap(height = 10.dp)
                                Text(
                                    text = stringResource(Res.string.about_book),
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                HeightGap(height = 5.dp)

                                val storyDetailsData = getStoryData(dataBn = htmlToString( data.summaryBn ?: ""), dataEn = htmlToString( data.summaryEn ?: ""))
                                if(storyDetailsData.isEmpty()){
                                    EmptyBookDetails(
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )
                                }else {
                                    SeymourText(
                                        onSeeMoreChange = { viewModel.isExpandedText = it },
                                        isSeeMoreExpanded = viewModel.isExpandedText,
                                        text = storyDetailsData,
                                        seeMoreText = stringResource(Res.string.see_more),
                                        seeLessText = stringResource(Res.string.see_less),
                                        seeLessStyle = SpanStyle(
                                            color = onPrimaryDark
                                        ),
                                        seeMoreStyle = SpanStyle(
                                            color = onPrimaryDark
                                        ),
                                        seeMoreMaxLines = 3,
                                        seeLessMaxLines = Int.MAX_VALUE,
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                            fontSize = adjustedFontSize(10f)
                                        )
                                    )
                                }

                                HeightGap(height = 10.dp)
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                                ) {
                                    QuickAccessButton(
                                        modifier = Modifier.weight(1f).clip(shape = CircleShape)
                                            .clickable {

                                            },
                                        icon = painterResource(Res.drawable.favorite_disable),
                                        title = stringResource(Res.string.add_favorites)
                                    )
                                    WidthGap(width = 5.dp)
                                    QuickAccessButton(
                                        modifier = Modifier.weight(1f).clip(shape = CircleShape)
                                            .clickable {
                                                if (backStack.contains(AppDestination.Dest.AllCategory)) {
                                                    backStack.removeLastOrNull()
                                                    backStack.removeLastOrNull()
                                                } else {
                                                    backStack.add(AppDestination.Dest.AllCategory)
                                                }

                                            },
                                        icon = painterResource(Res.drawable.icon_category),
                                        title = stringResource(Res.string.category)
                                    )
                                    WidthGap(width = 5.dp)
                                    QuickAccessButton(
                                        modifier = Modifier.weight(1f).clip(shape = CircleShape)
                                            .clickable {

                                                if (backStack.contains(AppDestination.Dest.AllReleaseStory)) {
                                                    backStack.removeLastOrNull()
                                                } else {
                                                    backStack.add(AppDestination.Dest.AllReleaseStory)
                                                }
                                            },
                                        icon = painterResource(Res.drawable.dashboard),
                                        title = stringResource(Res.string.all_release)
                                    )
                                }
                                HeightGap(height = 10.dp)
                                Text(
                                    text = stringResource(Res.string.similar_story),
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                HeightGap(height = 5.dp)

                                if (data.likeStories.isEmpty()) {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        EmptySimilarStory(modifier = Modifier.padding(vertical = 15.dp)){
                                            viewModel.getStoryDetails()
                                        }
                                    }
                                } else {
                                    LazyRow(modifier = Modifier.fillMaxWidth(), state = rememberLazyListState()) {
                                        items(data.likeStories, key = {it.storyID?: it.hashCode()}, contentType = {it.category_name}) { myLikeStory ->
                                            LikeStoryItem(item = myLikeStory, context = contextCoil)
                                        }
                                    }
                                }
                            }

                            if (viewModel.isOpenRatingBottomSheet) {
                                RatingBottomSheet(myBookItem = data, viewModel = viewModel) {
                                    viewModel.isOpenRatingBottomSheet = false
                                }
                            }
                        }
                    }

                    is UiState.Error -> {
                        ServerError {
                            viewModel.getStoryDetails()
                        }
                    }
                }




                }
            }
        }
    }


}


