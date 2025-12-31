package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.loas_state_append_error
import muktokowlomcmp.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadStateRefreshError(onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(Res.string.loas_state_append_error),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inverseSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(.8f)
            )
            HeightGap(10.dp)
            TextButton(onClick = {onRetry.invoke()}) {
                Text(
                    text = stringResource(Res.string.retry),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}

@Preview
@Composable
fun LoadStateRefreshErrorPreview() {
    LoadStateRefreshError(onRetry = {})
}