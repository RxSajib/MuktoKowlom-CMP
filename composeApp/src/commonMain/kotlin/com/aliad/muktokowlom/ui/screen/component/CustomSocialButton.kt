package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.facebook_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomSocialButton(icon : Painter, backGroundColor : Color, onClick: () -> Unit){
    Box(modifier = Modifier.size(40.dp).clip(shape = CircleShape).background(color = backGroundColor).clickable{onClick.invoke()}, contentAlignment = Alignment.Center){
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(.5f),
            colorFilter = ColorFilter.tint(color = Color.White)
        )
    }
}

@Composable
@Preview
fun CustomSocialButtonPreview(){
    CustomSocialButton(
        icon = painterResource(Res.drawable.facebook_icon),
        backGroundColor = Color.Blue,
        onClick = {}
    )
}