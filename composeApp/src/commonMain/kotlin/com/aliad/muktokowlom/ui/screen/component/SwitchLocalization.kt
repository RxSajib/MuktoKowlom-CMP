package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SwitchLocalization(onClickBn: () -> Unit, onClickEn: () -> Unit) {

    val dataStoreViewModel: DataStoreViewModel = koinViewModel()
    val selectLocal = dataStoreViewModel.getStringData(key = AppConstant.SELECT_LOCAL)
        .collectAsStateWithLifecycle("En")


    Row(modifier = Modifier.clip(shape = RoundedCornerShape(2.dp))) {
        Box(
            modifier = Modifier.background(color = Color.White).padding(2.dp).padding(0.5.dp)
                .clip(shape = RoundedCornerShape(2.dp)).clickable {
                dataStoreViewModel.saveStringData(key = AppConstant.SELECT_LOCAL, value = "en")
            }
                .background(color = if (selectLocal.value == "en") Color.Red else Color.Gray.copy(alpha = 0.3f)).padding(4.dp)
        ) {
            Text(
                text = "en",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = adjustedFontSize(10.0f),
                    color = if (selectLocal.value == "en") Color.White else Color.Gray.copy(alpha = 0.4f)
                ),
            )
        }
        Box(
            modifier = Modifier.background(color = Color.White).padding(2.dp).padding(0.5.dp)
                .clip(shape = RoundedCornerShape(2.dp)).clickable {
                dataStoreViewModel.saveStringData(key = AppConstant.SELECT_LOCAL, value = "bn")
            }
                .background(color = if (selectLocal.value == "bn") Color.Red else Color.Gray.copy(alpha = 0.3f)).padding(4.dp)
        ) {
            Text(
                text = "বাং",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = adjustedFontSize(10.0f),
                    color = if (selectLocal.value == "bn") Color.White else Color.Gray.copy(alpha = 0.4f)
                )
            )
        }
    }
}

@Composable
@Preview
fun SwitchLocalizationPreview() {
    SwitchLocalization(
        onClickBn = {},
        onClickEn = {}
    )
}