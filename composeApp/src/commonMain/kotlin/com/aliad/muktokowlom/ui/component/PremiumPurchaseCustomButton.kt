package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.muktokowlom.utils.getTitle
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.amount
import muktokowlomcmp.composeapp.generated.resources.buy
import muktokowlomcmp.composeapp.generated.resources.icon_unlock_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PremiumPurchaseCustomButton(modifier: Modifier, price : String, selectedLn : String, onClick: () -> Unit){
    Card(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp).fillMaxWidth(),
        border = BorderStroke(width = 1.dp, color = onPrimaryLight.copy(alpha = 0.08f)),
        colors = CardDefaults.cardColors(containerColor = onPrimaryLight.copy(alpha = 0.03f)),
        shape = CircleShape
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = stringResource(Res.string.amount),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = adjustedFontSize(10.0f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
                WidthGap(width = 5.dp)
                Text(
                    text = "৳",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                )
                WidthGap(width = 1.dp)
                Text(
                    text = getTitle(
                        selectLn = selectedLn,
                        title = price ,
                        titleBn = AppConstant.toBanglaDigits(price)
                    ),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            MyCustomButton(
                isEnable = true,
                onClickButton = {
                    onClick.invoke()
                },
                modifier = Modifier.weight(1f),
                title = stringResource(Res.string.buy),
                padding = 0.dp,
                leftIcon = painterResource(Res.drawable.icon_unlock_svgrepo_com)
            )
        }
    }
}

@Composable
@Preview
fun PremiumPurchaseCustomButtonPreview(){
    PremiumPurchaseCustomButton(
        price = "30",
        selectedLn = "ln",
        onClick = {},
        modifier = Modifier
    )
}