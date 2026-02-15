package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyCustomButton(
    isEnable: Boolean = true,
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    padding: Dp = 7.dp,
    showProgress: Boolean = false
) {

    Button(
        onClick = { onClickButton() },
        enabled = isEnable && !showProgress,
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = if (isEnable) backgroundColor else Color.Gray
        ),
        modifier = modifier.fillMaxWidth()
    ) {

        AnimatedContent(
            targetState = showProgress,
            label = "button_animation"
        ) { isLoading ->

            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.Red,
                    strokeWidth = 2.dp,
                    modifier = Modifier.padding(7.dp)
                        .size(20.dp),

                )
            } else {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W500
                    ),
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MyCustomButtonPreview() {
    MyCustomButton(title = "Login", modifier = Modifier, onClickButton = {}, showProgress = true)
}