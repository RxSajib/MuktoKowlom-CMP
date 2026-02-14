package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
fun MyCustomLottieAnim(lottieFile : String, size : Dp){
    val composition by rememberLottieComposition {
        LottieCompositionSpec.DotLottie(
            Res.readBytes(lottieFile)
        )
    }

    val progress by animateLottieCompositionAsState(composition)

    Image(
        painter = rememberLottiePainter(
            composition = composition,
            progress = { progress },
        ),
        contentDescription = "Lottie animation",
        modifier = Modifier.size(size)
    )
}

@Composable
@Preview
fun MyCustomLottieAnimPreview(){
    MyCustomLottieAnim(lottieFile = "files/success_check.lottie", size = 50.dp)
}