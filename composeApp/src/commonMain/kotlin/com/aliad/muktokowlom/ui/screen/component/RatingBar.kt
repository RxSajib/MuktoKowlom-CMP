package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.ic_round_star
import org.jetbrains.compose.resources.painterResource

@Composable
fun MyRatingBar(
    rating: Float = 5f,
    maxRating: Int = 5,
    onStarClick: (Int) -> Unit,
    isIndicator: Boolean = false,
    starSize : Dp = 50.dp,
) {
    Row {
        for (i in 1..maxRating) {
            if (i <= rating.toInt()) {
                // Full stars
                Icon(
                    painterResource(Res.drawable.ic_round_star),
                    tint = Color.Red,
                    contentDescription = null,
                    modifier = Modifier
                        .size(starSize)
                        .clickable(!isIndicator) {
                            onStarClick(i)
                        }
                )
            } else if (i == rating.toInt() + 1 && rating % 1 != 0f) {
                // Partial star
                PartialStar(fraction = rating % 1)
            } else {
                // Empty stars
                Icon(
                    painterResource(Res.drawable.ic_round_star),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(starSize)
                        .clickable(!isIndicator) {
                            onStarClick(i)
                        }
                )
            }
        }
    }
}


@Composable
private fun PartialStar(fraction: Float) {
    val customShape = FractionalClipShape(fraction)

    Box {
        Icon(
            painterResource(Res.drawable.ic_round_star),
            tint = Color.Red,
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Box(
            modifier = Modifier
                .graphicsLayer(
                    clip = true,
                    shape = customShape
                )
        ) {
            Icon(
                painterResource(Res.drawable.ic_round_star),
                tint = Color.Red,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}


private class FractionalClipShape(private val fraction: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = size.width * fraction,
                bottom = size.height
            )
        )
    }
}