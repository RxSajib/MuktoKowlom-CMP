package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.vip
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DiagonalCornerView(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFFF9800), // Orange
    icon: DrawableResource = Res.drawable.vip
) {
    Box(
        modifier = modifier
            .fillMaxWidth(.45f)
            .aspectRatio(1f)
            .clip(shape = RoundedCornerShape(topStart = 10.dp))

    ) {

        Canvas(modifier = Modifier.matchParentSize()) {
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width * 0.5f, 0f)
                lineTo(0f, size.height * 0.5f)
                close()
            }

            drawPath(
                path = path,
                color = backgroundColor
            )
        }

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(5.dp)
                .size(15.dp)
                .graphicsLayer(
                    rotationZ = -45f,
                )
        )
    }
}

@Composable
@Preview
fun DiagonalCornerViewPreview(){
    DiagonalCornerView()
}