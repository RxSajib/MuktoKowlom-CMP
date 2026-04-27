package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.compottie.DotLottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import muktokowlomcmp.composeapp.generated.resources.Res
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyCustomLottieAnim(lottieFile : String, modifier: Modifier, onrepet : Boolean = false, interaction : Int = 1){
    val composition by rememberLottieComposition {
        LottieCompositionSpec.DotLottie(
            Res.readBytes(lottieFile)
        )
    }

    val progress by animateLottieCompositionAsState(composition = composition, reverseOnRepeat = onrepet, iterations = interaction)

    Image(
        painter = rememberLottiePainter(
            composition = composition,
            progress = { progress },
        ),
        contentDescription = "Lottie animation",
        modifier = modifier.fillMaxSize().background(color = Color.Transparent),
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview
fun MyCustomLottieAnimPreview(){
    MyCustomLottieAnim(
        lottieFile = "files/success_check.lottie",
        modifier = Modifier.size(50.dp)
    )
}