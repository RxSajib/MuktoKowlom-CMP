package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aliad.model.Category
import muktokowlomcmp.composeapp.generated.resources.Res
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().height(150.dp).padding(5.dp).clickable{onClick.invoke()}
            .clip(shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center
    ) {



        AsyncImage(
            model = category.completedImageUrl,
            modifier = Modifier.fillMaxSize(),
            contentDescription = category.name,
            contentScale = ContentScale.FillBounds
        )


        Box(
            modifier = Modifier.fillMaxSize().background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0x90000000),
                        Color(0x90000000)
                    )
                )
            )
        )

        Text(
            text = category.name,
            modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )

    }
}

@Composable
@Preview
fun CategoryItem() {
    CategoryItem(
        category = Category(
            name = "Category one",
            name_bn = "Category bangla",
            id = 15,
            image = "imageurl"
        ), onClick = {})
}