package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.aliad.model.MyCategory
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryDark
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryDialogItem(
    category: MyCategory, context: PlatformContext, isSelected: Boolean = false, onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))
            .background(if (isSelected) onPrimaryDark.copy(alpha = 0.2f) else Color.Transparent)
            .clickable {
                onClick.invoke()
            }.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = context).data(category.completedImageUrl)
                .size(150).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(.5f).clip(shape = CircleShape).aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        HeightGap(height = 5.dp)
        Text(
            text = category.name,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = adjustedFontSize(8f), color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview
fun CategoryDialogItemPreview() {
    val contextCoil = LocalPlatformContext.current
    CategoryDialogItem(
        category = MyCategory(
            name = "Poem", name_bn = "Poem", id = 1, image = ""
        ), context = contextCoil, onClick = {})
}

