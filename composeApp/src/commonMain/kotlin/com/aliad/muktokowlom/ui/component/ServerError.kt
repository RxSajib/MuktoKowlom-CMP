package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.icon_refresh_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.oops
import muktokowlomcmp.composeapp.generated.resources.retry
import muktokowlomcmp.composeapp.generated.resources.something_went_wrong
import muktokowlomcmp.composeapp.generated.resources.something_went_wrong_details
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ServerError(onClick: () -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MyCustomLottieAnim(
            lottieFile = "files/no_internet_connection.lottie",
            modifier = Modifier.size(150.dp)
        )

        HeightGap(height = 15.dp)
        Text(
            text = stringResource(Res.string.oops),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = stringResource(Res.string.something_went_wrong),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = adjustedFontSize(12f),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.W500
            )
        )

        Text(
            text = stringResource(Res.string.something_went_wrong_details),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = adjustedFontSize(10f),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            ),
        )
        HeightGap(height = 15.dp)
        MyCustomButton(
            isEnable = true, title = stringResource(Res.string.retry),
            leftIcon = painterResource(Res.drawable.icon_refresh_svgrepo_com),
            onClickButton = {
    onClick.invoke()
            }
        )
    }
    }
}

@Composable
@Preview
fun ServerErrorPreview(){
    ServerError(
        onClick = {

        }
    )
}