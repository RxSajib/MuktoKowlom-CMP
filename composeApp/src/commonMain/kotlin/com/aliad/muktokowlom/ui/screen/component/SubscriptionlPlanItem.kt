package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliad.model.Subscription
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.membership_valid_days
import muktokowlomcmp.composeapp.generated.resources.premium_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SubscriptionPlanItem(selected : Boolean = false, subscription: Subscription, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = if(selected) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.inverseSurface.copy(0.2f),
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .clickable{onClick.invoke()}


            .padding(10.dp) , verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.premium_svgrepo_com),
            contentDescription = null
        )
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = subscription.name?:"",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W500
                )
            )
            HeightGap(height = 4.dp)
            Text(
                text = stringResource(Res.string.membership_valid_days, subscription.days?: "0"),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f)
                )
            )
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                )
            )
            WidthGap(width = 1.dp)
            Text(
                text = subscription.price?: "0",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W500
                )
            )
        }
    }
}

