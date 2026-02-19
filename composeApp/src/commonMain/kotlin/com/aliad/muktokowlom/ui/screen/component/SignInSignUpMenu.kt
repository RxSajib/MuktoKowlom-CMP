package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import muktokowlomcmp.composeapp.generated.resources.person_circle
import muktokowlomcmp.composeapp.generated.resources.sign_in
import muktokowlomcmp.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SignUpSignInMenu(signIn: () -> Unit, signUp: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth().drawBehind{
        val strokeWidth = 1.dp.toPx()
        val dashWidth = 7.dp.toPx()
        val dashGap = 2.dp.toPx()

        drawRoundRect(
            color = Color(0xFFDFD0FF),
            style = Stroke(
                width = strokeWidth,
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(dashWidth, dashGap),
                    0f
                )
            ),
            cornerRadius = CornerRadius(5.dp.toPx())
        )
    }.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(Res.drawable.person_circle),
            contentDescription = null,
            modifier = Modifier.size(60.dp).clip(shape = CircleShape)
        )

        Column(modifier = Modifier.weight(1f).padding(start = 10.dp)) {
            Text(
                text = stringResource(Res.string.muktokowlom),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W600
                )
            )

            HeightGap(height = 2.dp)

            Row {
                Text(
                    text = stringResource(Res.string.sign_in),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(10f),
                        fontWeight = FontWeight.W300
                    ),
                    modifier = Modifier.clickable{
                        signIn.invoke()
                    }
                )
                Text(
                    text = " / ",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(10f),
                        fontWeight = FontWeight.W300
                    )
                )
                Text(
                    text = stringResource(Res.string.sign_up),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(10f),
                        fontWeight = FontWeight.W300
                    ),
                    modifier = Modifier.clickable{
                        signUp.invoke()
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun SignUpSignInMenuPreview(){
    SignUpSignInMenu(
        signIn = {},
        signUp = {}
    )
}