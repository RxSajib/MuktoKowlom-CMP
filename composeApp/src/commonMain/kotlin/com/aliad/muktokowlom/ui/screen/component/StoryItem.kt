package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.aliad.model.MyBookItem
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StoryItem(item: MyBookItem?, onClick: (data: MyBookItem) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(10.dp))
            .clickable { onClick.invoke(item!!) }.padding(10.dp)
    ) {
        Box{

            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                model = item?.completedImageUri ?: "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.placeholder),
                error = painterResource(Res.drawable.placeholder)
            )
            if(item?.isPaidStory == true){
                DiagonalCornerView()
            }

        }

        HeightGap(10.dp)
        MyRatingBar(
            rating = item?.ratingToInt?.toFloat() ?: 0f,
            starSize = 15.dp,
            onStarClick = {},
            isIndicator = true
        )
        HeightGap(2.dp)
        Text(
            text = item?.titleBn ?: "Unknow Story",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W600,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item?.authorName ?: "Unknow Author", modifier = Modifier.fillMaxWidth(),
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f),
                fontSize = adjustedFontSize(10f)
            )
        )

    }
}

