package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.green
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.muktokowlom.ui.theme.purpleBlue
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.icon_power
import muktokowlomcmp.composeapp.generated.resources.icon_secure
import muktokowlomcmp.composeapp.generated.resources.icon_support
import muktokowlomcmp.composeapp.generated.resources.instant_access
import muktokowlomcmp.composeapp.generated.resources.instant_access_details
import muktokowlomcmp.composeapp.generated.resources.secure_payment
import muktokowlomcmp.composeapp.generated.resources.secure_payment_Details
import muktokowlomcmp.composeapp.generated.resources.support
import muktokowlomcmp.composeapp.generated.resources.support_details
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PremiumBenefits() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        PremiumBenefitsItem(
            title = stringResource(Res.string.secure_payment),
            details = stringResource(Res.string.secure_payment_Details),
            icon = painterResource(Res.drawable.icon_secure),
            color = purpleBlue,
            modifier = Modifier.weight(1f)
        )

        PremiumBenefitsItem(
            title = stringResource(Res.string.instant_access),
            details = stringResource(Res.string.instant_access_details),
            icon = painterResource(Res.drawable.icon_power),
            color = onPrimaryLight,
            modifier = Modifier.weight(1f)
        )
        PremiumBenefitsItem(
            title = stringResource(Res.string.support),
            details = stringResource(Res.string.support_details),
            icon = painterResource(Res.drawable.icon_support),
            color = green,
            modifier = Modifier.weight(1f)
        )
    }

}

@Composable
fun PremiumBenefitsItem(
    title: String,
    details: String,
    icon: Painter,
    color: Color,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.1f))
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                    tint = color
                )
            }

            WidthGap(width = 5.dp)

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = adjustedFontSize(8.0f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = details,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.8f),
                        fontSize = adjustedFontSize(6.0f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PremiumBenefitsItemPreview() {

    PremiumBenefits()
}