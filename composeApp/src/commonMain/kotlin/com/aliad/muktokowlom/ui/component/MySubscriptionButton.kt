package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.free
import muktokowlomcmp.composeapp.generated.resources.global
import muktokowlomcmp.composeapp.generated.resources.icon_crown
import muktokowlomcmp.composeapp.generated.resources.premium_details_two
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MySubscriptionButton(
    rightImage: Painter,
    height: Dp = 90.dp,
    title: String,
    details: String,
    isSelected: Boolean = false,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth().clip(shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = if (!isSelected) MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.3f) else onPrimaryLight,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = if (!isSelected) MaterialTheme.colorScheme.surface else onPrimaryLight.copy(
                    alpha = 0.01f
                )
            )
            .padding(8.dp).height(height = height),
        contentAlignment = Alignment.CenterEnd
    ) {
        Image(
            painter = rightImage,
            contentDescription = null,
            modifier = Modifier.height(height = height - 20.dp)
        )

        Row(modifier = Modifier.fillMaxSize()) {
            MyRadioButton(
                isSelected = isSelected,
                onClick = {
                },
            )

            Column(modifier = Modifier.weight(1f)) {
                HeightGap(height = 13.dp)
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                HeightGap(height = 5.dp)

                Text(
                    text = details,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(10.0f),
                        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MySubscriptionButtonPreview() {
    MySubscriptionButton(
        rightImage = painterResource(Res.drawable.icon_crown),
        title = stringResource(Res.string.free),
        details = stringResource(Res.string.premium_details_two),
        isSelected = true,
        modifier = Modifier
    ) {

    }
}