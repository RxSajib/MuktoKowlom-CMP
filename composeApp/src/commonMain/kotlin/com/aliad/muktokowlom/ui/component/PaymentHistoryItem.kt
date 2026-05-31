package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.model.Payment
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.bkashColor
import com.aliad.muktokowlom.ui.theme.green
import com.aliad.muktokowlom.utils.getTitle
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.amount
import muktokowlomcmp.composeapp.generated.resources.bkash_icon
import muktokowlomcmp.composeapp.generated.resources.end
import muktokowlomcmp.composeapp.generated.resources.paid
import muktokowlomcmp.composeapp.generated.resources.payment_method
import muktokowlomcmp.composeapp.generated.resources.success
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PaymentHistoryItem(payment: Payment?, selectedBn : String) {
    Column(
        modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.2f))
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.size(40.dp).clip(shape = CircleShape)
                        .background(color = AppConstant.paymentTypeToColor(type = payment?.cardType ?: "")), contentAlignment = Alignment.Center
                ) {
                    Image(
                       painter = painterResource(AppConstant.paymentTypeToImage(
                            type = payment?.cardType ?: ""
                        )),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(.7f).aspectRatio(1f),
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )
                }

                WidthGap(width = 10.dp)
                Column {
                    Text(
                        text = getTitle(selectLn = selectedBn, title = payment?.planName?: "", titleBn = payment?.planNameBn?: ""),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = adjustedFontSize(10f)
                        )
                    )

                    Text(
                        text = "${stringResource(Res.string.end)} ${payment?.expiryDate?: ""}",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            fontSize = adjustedFontSize(8f)
                        )
                    )
                }

            }
            Row(
                modifier = Modifier.clip(shape = CircleShape)
                    .background(color = green.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.success),
                    modifier = Modifier.size(15.dp),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = green)
                )

                WidthGap(width = 10.dp)
                Text(
                    text = stringResource(Res.string.paid),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(8f),
                        color = green
                    )
                )
            }
        }

        HeightGap(height = 10.dp)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(Res.string.amount),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(8f),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                )

                Text(
                    text = "৳${AppConstant.toBanglaDigits(payment?.amount?: "0")}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Text(
                    text = stringResource(Res.string.payment_method),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(8f),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter =painterResource(AppConstant.paymentTypeToImage(
                            type = payment?.cardType ?: ""
                        )),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = AppConstant.paymentTypeToColor(type = payment?.cardType ?: "")),
                        modifier = Modifier.size(30.dp)
                    )
                    WidthGap(width = 10.dp)
                    Text(
                        text = payment?.cardType?: "",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = AppConstant.paymentTypeToColor(type = payment?.cardType ?: ""),
                            fontSize = adjustedFontSize(10f)
                        )
                    )
                }
            }
        }
    }
}

