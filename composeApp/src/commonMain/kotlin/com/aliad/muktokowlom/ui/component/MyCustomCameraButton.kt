package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.camera_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyCustomCameraButton(){
    Box(modifier = Modifier.size(25.dp).clip(shape = CircleShape).background(color = onPrimaryLight).border(width = 2.dp, color = MaterialTheme.colorScheme.surface, shape = CircleShape), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(Res.drawable.camera_svgrepo_com),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(.5f).aspectRatio(1f),
            colorFilter = ColorFilter.tint(color = Color.White)
        )
    }
}

@Composable
@Preview
fun MyCustomCameraButtonPreview(){
    MyCustomCameraButton()
}