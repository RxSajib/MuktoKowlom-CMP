package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.choose_file
import muktokowlomcmp.composeapp.generated.resources.icon_file
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import muktokowlomcmp.composeapp.generated.resources.upload_story_file
import muktokowlomcmp.composeapp.generated.resources.upload_story_file_type
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StoryFileUploadCustomButton(
    title: String,
    fileType: String,
    fileIcon: Painter,
    fileButtonBgColor: Color,
    modifier: Modifier
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.drawBehind{
        val strokeWidth = 1.dp.toPx()
        val dashWidth = 7.dp.toPx()
        val dashGap = 2.dp.toPx()

        drawRoundRect(
            color = Color(0xFFDFD0FF),
            style = Stroke(
                width = strokeWidth,
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(dashWidth, dashGap),
                    0f
                )
            ),
            cornerRadius = CornerRadius(5.dp.toPx())
        )
    }.padding(10.dp)) {

        Box(
            modifier = Modifier.clip(shape = CircleShape)
                .background(color = fileButtonBgColor.copy(0.1f)).padding(10.dp)
        ) {
            Image(
                painter = fileIcon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = fileButtonBgColor),
                modifier = Modifier.size(20.dp),
            )
        }
        HeightGap(height = 10.dp)
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = fileType,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.inverseSurface,
                fontWeight = FontWeight.W300,
                fontSize = adjustedFontSize(9.0f)
            )
        )
        HeightGap(height = 10.dp)
        Box(
            modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                .border(width = 1.dp, color = onPrimaryLight, shape = RoundedCornerShape(5.dp))
                .clickable {

                }
                .padding(7.dp)

        ) {
            Text(
                text = stringResource(Res.string.choose_file),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = onPrimaryLight,
                    fontWeight = FontWeight.W500
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StoryFileUploadCustomButtonPreview() {
    StoryFileUploadCustomButton(
        title = stringResource(Res.string.upload_story_file),
        fileType = stringResource(Res.string.upload_story_file_type),
        fileIcon = painterResource(Res.drawable.icon_file),
        fileButtonBgColor = onPrimaryLight,
        modifier = Modifier
    )
}