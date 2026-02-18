package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MyCustomInputFiled(placeHolderText : String, text : String, onValueChange : (String) -> Unit, isPasswordInput : Boolean = false, isPasswordVisibility: Boolean = false, isVisiblePasswordChange : () -> Unit){
    OutlinedTextField(
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.W400
        ),
        value = text, onValueChange = { passwordInput ->
           onValueChange.invoke(passwordInput)
        },
        modifier = Modifier.fillMaxWidth().clip(shape = CircleShape).background(color = Color.Gray.copy(alpha = 0.1f)),
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        visualTransformation = if(!isPasswordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if(isPasswordInput){
                IconButton(onClick = {
                    isVisiblePasswordChange.invoke()
                }){
                    Icon(if(!isPasswordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = null)
                }
            }

        },
        placeholder = {
            Text(text = placeHolderText, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.W400
            ))
        },
        maxLines = 1,

    )
}