package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.edit
import muktokowlomcmp.composeapp.generated.resources.icon_pen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProfileEditButton(onClick: () -> Unit) {
    Card(
       shape = CircleShape,
        onClick = {
            onClick.invoke()
        }
    ) {

        val iconTint =  MaterialTheme.colorScheme.primary
        Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(Res.drawable.icon_pen),
                contentDescription = null,
                modifier = Modifier.size(15.dp),
                colorFilter = ColorFilter.tint(color = iconTint)
            )
            WidthGap(width = 6.dp)
            Text(
                text = stringResource(Res.string.edit),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = adjustedFontSize(10f),
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }

    }
}

@Composable
@Preview
fun ProfileEditButtonPreview() {
    ProfileEditButton(
        onClick = {}
    )
}