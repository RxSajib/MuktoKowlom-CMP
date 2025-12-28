package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyCustomButton(
    isEnable: Boolean = true,
    title: String,
    backgroundColor: Color = Color.Black,
    textColor: Color = Color.White,
    modifier: Modifier,
    onClickButton: () -> Unit
) {
    Button(
        onClick = {
            onClickButton.invoke()
        },
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = if(isEnable) MaterialTheme.colorScheme.primary else Color.Gray
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.W500
            ),
            modifier = Modifier.padding(7.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MyCustomButtonPreview() {
    MyCustomButton(title = "Login", modifier = Modifier, onClickButton = {})
}