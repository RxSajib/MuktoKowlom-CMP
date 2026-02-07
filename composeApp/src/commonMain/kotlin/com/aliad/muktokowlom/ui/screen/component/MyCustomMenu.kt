package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.arrow_right_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.premium
import muktokowlomcmp.composeapp.generated.resources.premium_details
import muktokowlomcmp.composeapp.generated.resources.premium_svgrepo_com
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyCustomMenu(
    modifier: Modifier,
    title: String,
    details: String,
    painter: Painter,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.border(
            width = 0.5.dp,
            shape = RoundedCornerShape(size = 10.dp),
            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
        ).clip(shape = RoundedCornerShape(size = 10.dp)).clickable { onClick.invoke() }
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f).padding(end = 10.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HeightGap(height = 5.dp)
            Text(
                text = details,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.inverseSurface
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Icon(
            painter = painterResource(Res.drawable.arrow_right_svgrepo_com),
            contentDescription = null
        )
    }
}

@Composable
@Preview
fun MyCustomMenuPreview() {
    MyCustomMenu(
        modifier = Modifier,
        title = stringResource(Res.string.premium),
        details = stringResource(Res.string.premium_details),
        painter = painterResource(Res.drawable.premium_svgrepo_com)
    ) {

    }
}