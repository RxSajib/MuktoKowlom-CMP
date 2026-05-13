package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import muktokowlomcmp.composeapp.generated.resources.no_results_found
import muktokowlomcmp.composeapp.generated.resources.no_results_found_details
import muktokowlomcmp.composeapp.generated.resources.nothing_saved
import muktokowlomcmp.composeapp.generated.resources.nothing_saved_details
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmptySaveBook(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MyCustomLottieAnim(
            lottieFile = "files/bookmark.lottie",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = stringResource(Res.string.nothing_saved),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = stringResource(Res.string.nothing_saved_details),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = adjustedFontSize(10f),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            )
        )
    }
}

@Preview
@Composable
fun EmptySaveBookPreview(){
    EmptySaveBook()
}