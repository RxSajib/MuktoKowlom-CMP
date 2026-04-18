package com.aliad.muktokowlom.ui.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.WidthGap
import kotlinx.coroutines.launch
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.are_you_sure
import muktokowlomcmp.composeapp.generated.resources.cancel
import muktokowlomcmp.composeapp.generated.resources.delete
import muktokowlomcmp.composeapp.generated.resources.delete_account_dialog_details
import muktokowlomcmp.composeapp.generated.resources.delete_account_dialog_title
import muktokowlomcmp.composeapp.generated.resources.delete_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountBottomSheet(
    isProgress: Boolean = false,
    deleteAccountButtonClick: () -> Unit,
    onDismissRequest: () -> Unit
) {

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() },
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(50.dp).clip(shape = CircleShape)
                    .background(color = Color.Red.copy(0.1f)), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.delete_svgrepo_com),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(.45f).aspectRatio(1f)
                )
            }
            HeightGap(height = 15.dp)
            Text(
                text = stringResource(Res.string.are_you_sure),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.W500
                )
            )
            HeightGap(height = 2.dp)
            Text(
                text = stringResource(Res.string.delete_account_dialog_title),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            HeightGap(height = 10.dp)
            Text(
                text = stringResource(Res.string.delete_account_dialog_details),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
            HeightGap(height = 10.dp)
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(16.dp)
            ) {
                MyCustomButton(
                    onClickButton = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            onDismissRequest.invoke()
                        }
                    },
                    title = stringResource(Res.string.cancel),
                    modifier = Modifier.weight(1f),
                    padding = 0.dp
                )
                WidthGap(width = 20.dp)
                MyCustomButton(
                    onClickButton = {
                        deleteAccountButtonClick.invoke()
                    }, backgroundColor = Color.Red, title = stringResource(Res.string.delete),
                    showProgress = isProgress,
                    modifier = Modifier.weight(1f), padding = 0.dp
                )
            }
        }
    }
}

@Composable
@Preview
fun DeleteAccountBottomSheetPreview() {
    DeleteAccountBottomSheet(
        deleteAccountButtonClick = { },
        onDismissRequest = {}
    )
}