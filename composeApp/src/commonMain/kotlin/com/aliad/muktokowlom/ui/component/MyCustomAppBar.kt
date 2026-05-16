package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.aliad.helper.ResetAppEvent.triggerResetEvent
import com.aliad.muktokowlom.platform.backButtonIcon
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import io.ktor.util.Platform
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.left_arrow_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.notification_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.person_circle
import muktokowlomcmp.composeapp.generated.resources.placeholder
import muktokowlomcmp.composeapp.generated.resources.premium_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.search_alt_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.user
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomAppBar(
    homeHeaderEnable: Boolean = false,
    isBackButtonEnable: Boolean = true,
    isActonButtonEnable: Boolean = false,
    title: String,
    onBackPress: () -> Unit,
    editProfile: () -> Unit,
    userName: String? = null,
    userEmailAddress: String? = null,
    userProfileImage: String? = null,
) {

    val contextCoil = LocalPlatformContext.current

    TopAppBar(
        title = {
            if (homeHeaderEnable) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = contextCoil).data(userProfileImage)
                            .size(200).build(),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp).clip(shape = CircleShape)
                            .clickable {
                                editProfile.invoke()
                            },
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(Res.drawable.person_circle),
                        error = painterResource(Res.drawable.person_circle)
                    )
                    WidthGap(15.dp)
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = userName ?: "",
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )

                        Text(
                            text = userEmailAddress ?: "",
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = adjustedFontSize(8.0f),
                                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.3f)
                            )
                        )
                    }
                }
            } else {
                Text(
                    text = title, style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

        },
        actions = {


        },
        navigationIcon = {
            if (isBackButtonEnable) {
                IconButton(onClick = {
                    onBackPress.invoke()
                }) {
                    Icon(
                        imageVector = backButtonIcon(),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

        },
    )
}

@Preview(name = "s")
@Composable
fun MyCustomAppBarPreview() {
    MyCustomAppBar(
        isBackButtonEnable = true,
        isActonButtonEnable = true,
        title = "Title Toolbar",
        homeHeaderEnable = true,
        onBackPress = {},
        editProfile = {},
    )
}