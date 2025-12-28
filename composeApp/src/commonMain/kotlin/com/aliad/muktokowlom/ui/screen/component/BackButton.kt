package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BackButton(imageVector: ImageVector, onclick: () -> Unit){
    IconButton(onClick = {}){
        Icon(imageVector = imageVector, contentDescription = null)
    }
}

@Composable
@Preview(showBackground = true)
fun SignInScreenPreview(){
    BackButton(
        imageVector = Icons.AutoMirrored.Default.ArrowBack,
        onclick = {}
    )
}
