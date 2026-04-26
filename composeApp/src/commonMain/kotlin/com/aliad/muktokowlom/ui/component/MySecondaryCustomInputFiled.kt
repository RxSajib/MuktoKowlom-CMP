package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MySecondaryCustomInputFiled(
    modifier: Modifier,
    placeholder: String,
    leadingIcon: Painter? = null,
    tralingIcon: Painter? = null
) {

    val data = remember { mutableStateOf("") }

    OutlinedTextField(
        value = data.value,
        onValueChange = {
            data.value = it
        },
        modifier = modifier.fillMaxWidth().border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.4f),
            shape = RoundedCornerShape(size = 5.dp)
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodySmall
            )
        },
        trailingIcon = {
            tralingIcon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

        }

    )
}

@Composable
@Preview
fun MySecondaryCustomInputFiledPreview() {
    MySecondaryCustomInputFiled(placeholder = "Enter name", modifier = Modifier)
}