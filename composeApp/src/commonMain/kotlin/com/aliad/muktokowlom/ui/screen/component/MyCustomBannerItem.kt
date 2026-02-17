package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
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
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyCustomBannerItem(){
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clip(shape = RoundedCornerShape(10.dp)).background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f)).padding(16.dp)) {
        Image(
            painter = painterResource(Res.drawable.muktokowlom),
            contentDescription = null,
            modifier = Modifier.weight(0.2f).aspectRatio(1f).clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
            Text(
                text = "Bangladesh",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

            )

            Text(
                text = "Bangladesh, to the east of India on the Bay of Bengal, is a South Asian country marked by lush greenery and many waterways. Its Padma (Ganges), Meghna and Jamuna rivers create fertile plains, and travel by boat",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.inverseSurface,
                ),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun MyCustomBannerItemPreview(){
    MyCustomBannerItem()
}