package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.empty_story
import muktokowlomcmp.composeapp.generated.resources.no_results_found
import muktokowlomcmp.composeapp.generated.resources.no_results_found_details
import muktokowlomcmp.composeapp.generated.resources.placeholder
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.try_again
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmptyStoryMessage(retryAgain: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MyCustomLottieAnim(
                lottieFile = "files/empty_state.lottie",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = stringResource(Res.string.no_results_found),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = stringResource(Res.string.no_results_found_details),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = adjustedFontSize(10f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                )
            )

            HeightGap(height = 10.dp)
            MyCustomButton(
                isEnable = true,
                leftIcon = painterResource(Res.drawable.search_alt_svgrepo_com),
                onClickButton = {
                    retryAgain.invoke()
                },
                title = stringResource(Res.string.try_again)
            )
        }

    }
}

@Preview
@Composable
fun EmptyStoryMessagePreview() {
    EmptyStoryMessage(
        retryAgain = {}
    )
}