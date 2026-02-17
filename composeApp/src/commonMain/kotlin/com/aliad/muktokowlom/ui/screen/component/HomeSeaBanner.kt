package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.dashboard_web_link
import muktokowlomcmp.composeapp.generated.resources.details_dashboard
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import muktokowlomcmp.composeapp.generated.resources.muktokowlom_white
import muktokowlomcmp.composeapp.generated.resources.placeholder
import muktokowlomcmp.composeapp.generated.resources.sea
import muktokowlomcmp.composeapp.generated.resources.title_dashboard
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeSeaBanner(onCLickWeb: () -> Unit){
    Box(modifier = Modifier.padding(horizontal = 16.dp
    ).fillMaxWidth().aspectRatio(3f).clip(shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(Res.drawable.sea),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f).padding(end = 16.dp)) {
                Text(
                    text = stringResource(Res.string.title_dashboard),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                HeightGap(height = 5.dp)
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(Res.string.details_dashboard),
                        modifier = Modifier,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White
                        )
                    )
                    Text(
                        text = stringResource(Res.string.dashboard_web_link),
                        modifier = Modifier,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Red,
                        )
                    )
                }
            }
            Image(
                painter = painterResource(Res.drawable.muktokowlom_white),
                contentDescription = null,
                modifier = Modifier.size(60.dp).aspectRatio(1f)
            )
        }
    }
}

@Composable
@Preview
fun HomeSeaBannerPreview(){
    HomeSeaBanner{

    }
}