package com.aliad.muktokowlom.ui.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.WidthGap
import kotlinx.coroutines.launch
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.cancel
import muktokowlomcmp.composeapp.generated.resources.logout
import muktokowlomcmp.composeapp.generated.resources.logout_dialog_details
import muktokowlomcmp.composeapp.generated.resources.logout_dialog_title
import muktokowlomcmp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutBottomSheet(onDismissRequest: () -> Unit, logoutButtonClick: () -> Unit) {

    val state = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(onDismissRequest = { onDismissRequest.invoke() }, sheetState = state) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

                Text(
                    text = stringResource(Res.string.logout_dialog_title),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W500
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                HeightGap(height = 10.dp)
                Text(
                    text = stringResource(Res.string.logout_dialog_details),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            HeightGap(height = 10.dp)

            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f))
                    .padding(16.dp)
            ) {
                MyCustomButton(
                    title = stringResource(Res.string.cancel),
                    modifier = Modifier.weight(1f),
                    onClickButton = {
                        scope.launch {
                            state.hide()
                            onDismissRequest.invoke()
                        }

                    },
                    isEnable = true,
                    padding = 0.dp
                )
                WidthGap(width = 20.dp)
                MyCustomButton(
                    backgroundColor = Color.Red,
                    title = stringResource(Res.string.logout),
                    modifier = Modifier.weight(1f),
                    onClickButton = {
                        scope.launch {
                            state.hide()
                            logoutButtonClick.invoke()
                            onDismissRequest.invoke()
                        }
                    },
                    isEnable = true,
                    padding = 0.dp
                )
            }
        }
    }
}

@Composable
@Preview
fun LogoutBottomSheetPreview() {
    LogoutBottomSheet(
        onDismissRequest = {},
        logoutButtonClick = {}
    )
}