package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PaymentHistoryShimmer() {
    Column(modifier = Modifier.fillMaxSize()) {
        repeat(15){
            PaymentHistoryShimmerItem()
        }
    }
}

@Composable
fun PaymentHistoryShimmerItem() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.size(45.dp).clip(shape = CircleShape).shimmer()
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))

            )

            WidthGap(width = 10.dp)
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Box(
                    modifier = Modifier.fillMaxWidth(.95f).height(15.dp).shimmer()
                        .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
                )

                HeightGap(height = 5.dp)

                Box(
                    modifier = Modifier.fillMaxWidth(.65f).height(15.dp).shimmer()
                        .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
                )
            }
        }

        HeightGap(height = 10.dp)

        Box(
            modifier = Modifier.fillMaxWidth(.5f).height(25.dp).shimmer()
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
        )
    }
}

@Composable
@Preview
fun PaymentHistoryShimmerPreview() {
    PaymentHistoryShimmer()
}

