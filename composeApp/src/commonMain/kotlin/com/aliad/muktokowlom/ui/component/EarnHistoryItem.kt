package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.model.MyEarnHistory
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.bkashColor
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.bkash_icon
import muktokowlomcmp.composeapp.generated.resources.money_cash
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EarnHistoryItem(myEarnHistory: MyEarnHistory, selectedLan : String) {

    fun generateRandomColor(): Color {
        return Color(
            red = (0..255).random(),
            green = (0..255).random(),
            blue = (0..255).random()
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth().border(
            width = 1.dp, color = MaterialTheme.colorScheme.inverseSurface.copy(
                0.2f
            ),
            shape = CircleShape
        ).padding(10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(shape = CircleShape).background(generateRandomColor().copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.money_cash),
                    modifier = Modifier.fillMaxWidth(.5f).aspectRatio(1f),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = generateRandomColor())
                )
            }
            WidthGap(width = 10.dp)
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = myEarnHistory.cardType,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = myEarnHistory.storyNameBn,
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(10f),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                )
            }

            Text(
                text = "৳${if (selectedLan == "en") myEarnHistory.amount else AppConstant.toBanglaDigits(myEarnHistory.amount)}",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }


    }
}

@Composable
@Preview(showBackground = true)
fun EarnHistoryItemPreview() {
    EarnHistoryItem(
        selectedLan = "en",
        myEarnHistory = MyEarnHistory(
            amount = "50",
            cardType = "DBBL-NEXUS",
            id = 1,
            storyNameBn = "Amar first coda",
            createAt = "2024-11-20T13:16:38.000000Z",
            views = "25",
            userId = "5"
        )
    )
}