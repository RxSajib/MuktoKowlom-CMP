package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom_white
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryShimmer() {
    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(1.5f).clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
            .shimmer()
    ) {
        Image(
            painter = painterResource(Res.drawable.muktokowlom_white),
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
@Preview
fun CategoryShimmerPreview() {
    CategoryShimmer()
}