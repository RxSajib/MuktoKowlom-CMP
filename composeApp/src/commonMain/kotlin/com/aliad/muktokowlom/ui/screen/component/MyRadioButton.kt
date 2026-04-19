package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyRadioButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    isEnable :  Boolean = true
) {
    RadioButton(
        selected = isSelected,
        onClick = { onClick.invoke() },
        colors = androidx.compose.material3.RadioButtonDefaults.colors(
            selectedColor = onPrimaryLight,
            unselectedColor =  Color.Gray.copy(alpha = 0.5f)
        ),
        modifier = modifier,
        //  enabled = isEnable
    )

}

@Preview
@Composable
private fun Preview() {
    MyRadioButton(isSelected = true, onClick = {}, isEnable = true)
}