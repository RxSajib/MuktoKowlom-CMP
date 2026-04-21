package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.model.Subscription
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.utils.getTitle
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.membership_valid_days
import muktokowlomcmp.composeapp.generated.resources.premium_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SubscriptionPlanItem(
    selected: Boolean = false,
    subscription: Subscription,
    selectLn: State<String>,
    onClick: () -> Unit
) {



    Row(
        modifier = Modifier.fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = if (selected) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.inverseSurface.copy(
                    0.2f
                ),
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .clickable { onClick.invoke() }


            .padding(10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.premium_svgrepo_com),
            contentDescription = null,
            tint = if (selected) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.inverseSurface.copy(
                alpha = 0.7f
            )
        )
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = getTitle(selectLn = selectLn.value, title = subscription.name ?: "", titleBn = subscription.name_bn ?: "") ,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (selected) FontWeight.W500 else FontWeight.W400
                )
            )
            HeightGap(height = 4.dp)
            Text(
                text = stringResource(Res.string.membership_valid_days, if(selectLn.value == "en") subscription.days ?: "0" else AppConstant.toBanglaDigits(subscription.days ?: "0")),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = if (selected) MaterialTheme.colorScheme.inverseSurface.copy(0.5f) else MaterialTheme.colorScheme.inverseSurface.copy(
                        0.3f
                    )
                )
            )
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "৳",
                style = MaterialTheme.typography.bodyLarge.copy(
                    // color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                    fontWeight = if (selected) FontWeight.W500 else FontWeight.W300
                )
            )
            WidthGap(width = 1.dp)
            Text(
                text = if(selectLn.value == "en") subscription.price ?: "0" else AppConstant.toBanglaDigits(subscription.price ?: "0"),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = if (selected) FontWeight.W500 else FontWeight.W300
                )
            )
        }
    }
}

