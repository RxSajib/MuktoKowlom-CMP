package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.valentinilk.shimmer.shimmer
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom_white
import muktokowlomcmp.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StoryItemShimmer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().shimmer().clip(shape = RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(100.dp)
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
                .clip(shape = RoundedCornerShape(10.dp))
        ){
            Image(
                painter = painterResource(Res.drawable.muktokowlom_white),
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(16.dp)
            )
        }
        HeightGap(20.dp)
        Box(
            modifier = Modifier.fillMaxWidth(.5f).height(10.dp)
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
        )
        HeightGap(10.dp)

        Box(
            modifier = Modifier.fillMaxWidth().height(30.dp)
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
        )
    }
}

@Composable
fun StoryShimmerRow(){
    Row {
        StoryItemShimmer(modifier = Modifier.weight(1f))
        WidthGap(16.dp)
        StoryItemShimmer(modifier = Modifier.weight(1f))
    }
}

@Composable
@Preview
fun StoryItemShimmerPreview() {
    StoryShimmerRow()
}