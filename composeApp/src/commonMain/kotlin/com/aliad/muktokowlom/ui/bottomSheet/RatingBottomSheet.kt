package com.aliad.muktokowlom.ui.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.aliad.muktokowlom.ui.component.FeedBackInputComponent
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.MyCustomButton
import com.aliad.muktokowlom.ui.component.MyRatingBar
import com.aliad.muktokowlom.ui.component.MySecondaryCustomInputFiled
import com.aliad.muktokowlom.ui.component.WidthGap
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.presentation.signIn.ui.storyDetails.StoryDetailsViewModel
import kotlinx.coroutines.launch
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.how_was_your_experienced
import muktokowlomcmp.composeapp.generated.resources.icon_send
import muktokowlomcmp.composeapp.generated.resources.muktokowlom_white
import muktokowlomcmp.composeapp.generated.resources.no_results_found
import muktokowlomcmp.composeapp.generated.resources.star_fill
import muktokowlomcmp.composeapp.generated.resources.start_writing_your_story
import muktokowlomcmp.composeapp.generated.resources.submit
import muktokowlomcmp.composeapp.generated.resources.tap_a_star_to_rate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingBottomSheet(viewModel: StoryDetailsViewModel, onDismissRequest: () -> Unit) {

    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val storyData = viewModel.storyData.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        try {
            state.expand()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() },
        containerColor = MaterialTheme.colorScheme.inversePrimary,
        sheetState = state
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AsyncImage(
                modifier = Modifier.fillMaxWidth(.65f).aspectRatio(2f)
                    .clip(shape = RoundedCornerShape(10.dp)),
                model = storyData.value.completedImageUri,
                contentDescription = null,
                placeholder = painterResource(Res.drawable.muktokowlom_white),
                error = painterResource(Res.drawable.muktokowlom_white),
                contentScale = ContentScale.Crop,

                )
            HeightGap(height = 15.dp)
            Text(
                text = storyData.value.titleBn ?: "",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            HeightGap(height = 10.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clip(shape = CircleShape).background(color = onPrimaryLight.copy(alpha = 0.1f))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.star_fill),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    colorFilter = ColorFilter.tint(color = onPrimaryLight)
                )
                WidthGap(5.dp)
                Text(
                    text = "${storyData.value.rating} / 0.5",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(10.0f),
                        fontWeight = FontWeight.Bold,
                        color = onPrimaryLight
                    )
                )
            }
            HeightGap(height = 2.dp)
            Text(
                text = storyData.value.user?.name ?: "",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = adjustedFontSize(8.0f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                )
            )
            HeightGap(height = 5.dp)
            Text(
                text = stringResource(Res.string.how_was_your_experienced),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = adjustedFontSize(12f)
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(Res.string.tap_a_star_to_rate),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    fontSize = adjustedFontSize(10f)
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                textAlign = TextAlign.Center
            )
            HeightGap(height = 10.dp)
            MyRatingBar(
                rating = viewModel.inputRatingCount,
                starSize = 25.dp,
                onStarClick = { newRatingInput ->
                    viewModel.inputRatingCount = newRatingInput.toFloat()
                },
                isIndicator = false
            )
            HeightGap(height = 10.dp)

            MySecondaryCustomInputFiled(
                placeholder = stringResource(Res.string.start_writing_your_story),
                modifier = Modifier.height(100.dp),
                inputData = viewModel.inputComment,
                onValueChanged = { fullStory ->
                    viewModel.inputComment = fullStory
                }
            )
            HeightGap(height = 15.dp)
            MyCustomButton(
                modifier = Modifier.fillMaxWidth(),
                isEnable = viewModel.isEnableRatingButton,
                title = stringResource(Res.string.submit),
                leftIcon = painterResource(Res.drawable.icon_send),
                onClickButton = {
                    scope.launch {
                        state.hide()
                        onDismissRequest.invoke()
                    }
                }
            )
            HeightGap(height = 15.dp)
        }
    }
}


