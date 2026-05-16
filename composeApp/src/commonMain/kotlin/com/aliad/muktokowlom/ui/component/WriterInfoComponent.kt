package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import io.ktor.util.Platform
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.about_author
import muktokowlomcmp.composeapp.generated.resources.placeholder
import muktokowlomcmp.composeapp.generated.resources.view_all
import muktokowlomcmp.composeapp.generated.resources.view_all_story
import muktokowlomcmp.composeapp.generated.resources.views
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WriterInfo(
    modifier: Modifier,
    writerName: String,
    profileImage: String,
    context : PlatformContext,
    viewAllButtonClick: () -> Unit
) {


    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(profileImage).size(200).build(),
            contentDescription = null,
            modifier = Modifier.size(50.dp).clip(shape = CircleShape),
            placeholder = painterResource(Res.drawable.placeholder),
            error = painterResource(Res.drawable.placeholder),
            contentScale = ContentScale.Crop
        )
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Text(
                text = writerName,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.primary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(Res.string.about_author),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = adjustedFontSize(8f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        WidthGap(width = 10.dp)
        Text(
            text = stringResource(Res.string.view_all_story),
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.White
            ),
            modifier = Modifier.clip(shape = CircleShape)
                .background(color = MaterialTheme.colorScheme.primary).clickable {
                    viewAllButtonClick.invoke()
                }
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )

    }
}

@Composable
@Preview
fun WriterInfoPreview() {
    val context = LocalPlatformContext.current
    WriterInfo(
        modifier = Modifier,
        writerName = "Sajib Roy",
        profileImage = "",
        context = context
    ) {

    }
}