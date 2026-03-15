package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SubscriptionItemShimmer() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp).shimmer(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(50.dp)
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f)).shimmer()
        )
        WidthGap(width = 10.dp)

        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier.fillMaxWidth().height(15.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f)).shimmer()
            )
            HeightGap(height = 10.dp)
            Box(
                modifier = Modifier.fillMaxWidth(.4f).height(15.dp)
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f)).shimmer()
            )
        }

    }
}

@Composable
fun SubscriptionShimmer(){
    Column {
        repeat(20){
            SubscriptionItemShimmer()
        }
    }
}

@Preview
@Composable
fun SubscriptionItemShimmerPreview() {
    SubscriptionItemShimmer()
}