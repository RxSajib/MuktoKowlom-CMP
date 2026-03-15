package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.model.Payment
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.data.app_constant.AppConstant.paymentTypeToImage
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.orange
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.bkash_bkash_logo
import muktokowlomcmp.composeapp.generated.resources.expired_on
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SubscriptionHistoryItem(payment: Payment) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = MaterialTheme.colorScheme.background).padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(paymentTypeToImage(type = payment.cardType)),
            contentDescription = null,
            modifier = Modifier.width(50.dp).height(25.dp)
        )
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = payment.planName,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            HeightGap(height = 5.dp)
            Row(
                modifier = Modifier.clip(shape = CircleShape).background(color = orange).border(width = 0.5.dp, color = Color.White, shape = CircleShape)
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.expired_on),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(10.0f)
                    )
                )
                WidthGap(width = 2.dp)
                Text(
                    text = payment.expiryDate,//com.sajib.data.appConstant.AppConstant.formatDate(input = payment.createdDate),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontSize = adjustedFontSize(10.0f)
                    )
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "$", style = MaterialTheme.typography.bodySmall.copy(
                fontSize = adjustedFontSize(10.0f)
            ))
            WidthGap(width = 2.dp)
            Text(text = payment.amount, style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ))
        }
    }
}


@Composable
@Preview
fun SubscriptionHistoryItemPreview() {
    SubscriptionHistoryItem(
        payment = Payment(
            amount = "100",
            cardType = "bKash-bKash",
            id = 112,
            planName = "Daily Package",
            planNameBn = "একদিনের প্যাকেজ",
            transactionID = "MUK177185254736677279897",
            userId = "4845",
            userName = "Aliad Polok",
            expiryDate = "2026-02-24",
            createdDate = "2026-02-24"
        )
    )
}