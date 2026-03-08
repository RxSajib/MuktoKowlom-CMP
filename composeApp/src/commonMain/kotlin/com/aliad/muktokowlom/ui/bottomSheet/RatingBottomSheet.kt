package com.aliad.muktokowlom.ui.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom_white
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingBottomSheet(onDismissRequest: () -> Unit) {

    ModalBottomSheet(onDismissRequest = { onDismissRequest.invoke() }) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(modifier = Modifier.fillMaxWidth(.4f).aspectRatio(2f).clip(shape = RoundedCornerShape(10.dp)),
                model = "",
                contentDescription = null,
                placeholder = painterResource(Res.drawable.muktokowlom_white),
                error = painterResource(Res.drawable.muktokowlom_white)
                )
            HeightGap(height = 15.dp)
            Text(
                text = ""
            )
        }
    }
}

@Composable
@Preview
fun RatingBottomSheetPreview() {
    RatingBottomSheet(onDismissRequest = {})
}