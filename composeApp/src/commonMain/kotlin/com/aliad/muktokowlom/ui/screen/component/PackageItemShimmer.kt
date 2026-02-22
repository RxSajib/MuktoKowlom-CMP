package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.model.Subscription
import com.valentinilk.shimmer.shimmer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SubscriptionPlanItemShimmer(

) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp).shimmer()
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f),
                shape = CircleShape
            )
            .clip(shape = CircleShape)


            .padding(10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(25.dp)
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
        )
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier.fillMaxWidth(.98f).height(15.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f)),
            )
            HeightGap(height = 10.dp)
            Box(
                modifier = Modifier.fillMaxWidth(.8f).height(10.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f)),
            )
        }
    }
}


@Composable
@Preview
fun PackageItemShimmerPreview() {
    SubscriptionPlanItemShimmer()
}