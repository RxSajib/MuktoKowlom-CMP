package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ShimmerBox() {
    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(2.5f).clip(shape = RoundedCornerShape(16.dp))
            .shimmer()
            .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
    )
}

@Composable
@Preview
fun ShimmerBoxPreview() {
    ShimmerBox()
}