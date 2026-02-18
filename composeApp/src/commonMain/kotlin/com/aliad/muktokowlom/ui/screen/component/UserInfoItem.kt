package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.phone_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun UserInfoItem(icon : Painter, title : String, isDivider : Boolean = true){

    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = icon, contentDescription = null)
            WidthGap(width = 10.dp)
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if(isDivider){
            HorizontalDivider(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.3f)))
        }
    }

}

@Composable
@Preview
fun UserInfoItemPreview(){
    UserInfoItem(
        icon = painterResource(Res.drawable.phone_svgrepo_com),
        title = "+8801771330378"
    )
}