package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.loas_state_append_error
import muktokowlomcmp.composeapp.generated.resources.retry
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadStateAppendError(retry: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(Res.string.loas_state_append_error),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseSurface
        )

        TextButton(onClick = {retry.invoke()}, modifier = Modifier.padding(start = 10.dp)){
            Text(text = stringResource(Res.string.retry),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview
@Composable
fun LoadStateAppendErrorPreview(){
    LoadStateAppendError(retry = {})
}