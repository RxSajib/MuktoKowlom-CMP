package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.empty_story_details
import muktokowlomcmp.composeapp.generated.resources.info
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmptyBookDetails(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(Res.drawable.info),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
        )
        WidthGap(width = 10.dp)
        Text(
            text = stringResource(Res.string.empty_story_details),
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = adjustedFontSize(8f),
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
@Preview
fun EmptyBookDetailsPreview() {
    EmptyBookDetails(
        modifier = Modifier
    )
}