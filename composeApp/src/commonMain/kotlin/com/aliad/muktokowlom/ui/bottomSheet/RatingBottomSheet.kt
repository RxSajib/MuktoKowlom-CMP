package com.aliad.muktokowlom.ui.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aliad.muktokowlom.ui.screen.component.FeedBackInputComponent
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.MyRatingBar
import com.aliad.muktokowlom.ui.screen.component.WidthGap
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.presentation.signIn.ui.storyDetails.StoryDetailsViewModel
import kotlinx.coroutines.launch
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.how_was_your_experienced
import muktokowlomcmp.composeapp.generated.resources.muktokowlom_white
import muktokowlomcmp.composeapp.generated.resources.star_fill
import muktokowlomcmp.composeapp.generated.resources.submit
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingBottomSheet(viewModel: StoryDetailsViewModel, onDismissRequest: () -> Unit) {

    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    LaunchedEffect(Unit) {
        try {
            state.expand()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() },
        containerColor = MaterialTheme.colorScheme.surface,
        sheetState = state
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AsyncImage(
                modifier = Modifier.fillMaxWidth(.4f).aspectRatio(2f)
                    .clip(shape = RoundedCornerShape(10.dp)),
                model = "",
                contentDescription = null,
                placeholder = painterResource(Res.drawable.muktokowlom_white),
                error = painterResource(Res.drawable.muktokowlom_white)
            )
            HeightGap(height = 15.dp)
            Text(
                text = "My Name Is a Story: An Empowering First Day of School Book for Kids",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            HeightGap(height = 10.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(Res.drawable.star_fill),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                WidthGap(10.dp)
                Text(
                    text = "0.0 / 0.5",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(10.0f),
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            HeightGap(height = 5.dp)
            Text(
                text = "Sajib Roy",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                )
            )
            HeightGap(height = 10.dp)
            Text(
                text = stringResource(Res.string.how_was_your_experienced),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                textAlign = TextAlign.Center
            )
            HeightGap(height = 15.dp)
            MyRatingBar(
                rating = viewModel.inputRatingCount,
                starSize = 25.dp,
                onStarClick = {newRatingInput ->
                    viewModel.inputRatingCount = newRatingInput.toFloat()
                },
                isIndicator = false
            )
            HeightGap(height = 15.dp)
            FeedBackInputComponent(
                actualValue = viewModel.inputComment,
                onValueChange = { newText ->
                    viewModel.inputComment = newText
                }
            )
            HeightGap(height = 15.dp)
            MyCustomButton(
                isEnable = viewModel.isEnableRatingButton,
                title = stringResource(Res.string.submit),
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


