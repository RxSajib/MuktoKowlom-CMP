package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.muktokowlom.utils.AppHelper.getPackageIconBasedOnPackageBadge
import com.aliad.muktokowlom.utils.AppHelper.getPackageInfoBasedOnPackage
import com.aliad.muktokowlom.utils.AppHelper.getPackageLottieVisibility
import com.aliad.muktokowlom.utils.AppHelper.getPackageTextBasedOnPackageBadge
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PremiumSelectedBanner(modifier: Modifier, packageName: String, borderColor: Color, selected : Boolean = false) {

    val type = getPackageTextBasedOnPackageBadge(packageName)

    Card(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
          //  containerColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(1.dp, color = borderColor),
        modifier = modifier.clip(shape = CircleShape).height(25.dp).width(100.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            if(getPackageLottieVisibility(type)){

                MyCustomLottieAnim(
                    lottieFile = "files/confetti.lottie",
                    modifier = Modifier.fillMaxSize(),
                    onrepet = false,
                    interaction = Int.MAX_VALUE
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(getPackageIconBasedOnPackageBadge(type)),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
                WidthGap(3.dp)
                Text(
                    text = stringResource(getPackageInfoBasedOnPackage(type)),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(8.0f),
                        fontWeight = if(selected) FontWeight.W600 else FontWeight.W400,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }

}

@Composable
@Preview
fun PremiumSelectedBannerPreview() {
    PremiumSelectedBanner(
        modifier = Modifier,
        packageName = "Daily Package",
        borderColor = Color.Red,
    )
}