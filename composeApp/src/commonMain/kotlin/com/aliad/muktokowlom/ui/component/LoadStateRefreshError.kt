package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.buy
import muktokowlomcmp.composeapp.generated.resources.icon_refresh_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.icon_unlock_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.loas_state_append_error
import muktokowlomcmp.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadStateRefreshError(onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(Res.string.loas_state_append_error),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = adjustedFontSize(10f)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(.8f)
            )
            HeightGap(10.dp)

            MyCustomButton(
                isEnable = true,
                onClickButton = {
                    onRetry.invoke()
                },
                modifier = Modifier,
                title = stringResource(Res.string.retry),
                padding = 0.dp,
                leftIcon = painterResource(Res.drawable.icon_refresh_svgrepo_com)
            )

        }
    }
}

@Preview
@Composable
fun LoadStateRefreshErrorPreview() {
    LoadStateRefreshError(onRetry = {})
}