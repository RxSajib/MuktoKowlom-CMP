package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.icon_users
import muktokowlomcmp.composeapp.generated.resources.what_do_you_want_for_readers
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StoryUploadCustomHeader(icon : Painter, title : String){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f))
        )
        WidthGap(width = 8.dp)
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
@Preview
fun StoryUploadCustomHeaderPreview(){
    StoryUploadCustomHeader(
        icon = painterResource(Res.drawable.icon_users),
        title = stringResource(Res.string.what_do_you_want_for_readers)
    )
}