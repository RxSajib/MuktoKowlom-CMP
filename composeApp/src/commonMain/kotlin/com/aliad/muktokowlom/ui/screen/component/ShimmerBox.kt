package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ShimmerBox() {
    Row(
        modifier = Modifier.fillMaxWidth().aspectRatio(2.5f).clip(shape = RoundedCornerShape(16.dp))
            .shimmer()
            .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier.fillMaxHeight(.5f).aspectRatio(1f)
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
                .clip(shape = RoundedCornerShape(10.dp))
        )

        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier.fillMaxWidth().height(30.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
            )
            HeightGap(height = 10.dp)
            Box(
                modifier = Modifier.fillMaxWidth(.8f).height(15.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
            )
        }
    }
}

@Composable
@Preview
fun ShimmerBoxPreview() {
    ShimmerBox()
}