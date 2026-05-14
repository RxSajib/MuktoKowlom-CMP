package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliad.model.Subscription
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.muktokowlom.utils.getTitle
import com.aliad.presentation.signIn.ui.subscriptionPlan.SubscriptionPlanViewModel
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
    mViewModel: SubscriptionPlanViewModel,
    onClick: () -> Unit,
) {


    fun generateRandomColor(): Color {
        return Color(
            red = (0..255).random(),
            green = (0..255).random(),
            blue = (0..255).random()
        )
    }


    Box(modifier = Modifier.fillMaxWidth()) {




        Column {
            HeightGap(height = 12.dp)
            Row(
                modifier = Modifier.fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (selected) onPrimaryLight else MaterialTheme.colorScheme.inverseSurface.copy(
                            0.2f
                        ),
                        shape = CircleShape
                    )
                    .clip(shape = CircleShape)
                    .clickable { onClick.invoke() }


                    .padding(15.dp), verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.clip(shape = CircleShape).background(color = generateRandomColor().copy(alpha = 0.1f)).padding(8.dp),
                    contentAlignment = Alignment.Center){
                    Icon(
                        painter =  painterResource(Res.drawable.premium_svgrepo_com),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = generateRandomColor()
                    )
                }

                WidthGap(width = 10.dp)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = getTitle(
                            selectLn = selectLn.value,
                            title = subscription.name ,
                            titleBn = subscription.name_bn
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.W400,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    HeightGap(height = 1.dp)
                    Text(
                        text = stringResource(
                            Res.string.membership_valid_days,
                            if (selectLn.value == "en") subscription.days
                                ?: "0" else AppConstant.toBanglaDigits(subscription.days ?: "0")
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (selected) MaterialTheme.colorScheme.inverseSurface.copy(0.5f) else MaterialTheme.colorScheme.inverseSurface.copy(
                                0.3f
                            ),
                            fontSize = adjustedFontSize(10.0f)
                        )
                    )
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "৳",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            // color = MaterialTheme.colorScheme.inverseSurface.copy(0.5f),
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.W300,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    WidthGap(width = 1.dp)
                    Text(
                        text =  getTitle(
                            selectLn = selectLn.value,
                            title = subscription.price ,
                            titleBn = AppConstant.toBanglaDigits(subscription.price ?: "0")
                        ),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.W300,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
        Row {
            WidthGap(width = 30.dp)
            PremiumSelectedBanner(
                modifier = Modifier,
                selected = selected,
                packageName =subscription.name?: "",
                borderColor  = if (selected) onPrimaryLight else MaterialTheme.colorScheme.inverseSurface.copy(
                    0.2f
                )
            )
        }

    }
}

