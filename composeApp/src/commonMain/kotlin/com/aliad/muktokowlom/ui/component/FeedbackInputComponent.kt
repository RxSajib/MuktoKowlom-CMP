package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FeedBackInputComponent(actualValue : String, onValueChange: (String) -> Unit){
    OutlinedTextField(
        onValueChange = {value ->
            onValueChange.invoke(value)
        },
        value = actualValue,
        modifier = Modifier.fillMaxWidth().aspectRatio(3f),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor =  Color.Gray.copy(alpha = 0.1f)
        )
    )
}

@Composable
@Preview
fun FeedBackInputComponentPreview(){
    _root_ide_package_.com.aliad.muktokowlom.ui.component.FeedBackInputComponent(
        actualValue = "",
        onValueChange = {}
    )
}