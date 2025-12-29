package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
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
import com.aliad.muktokowlom.platform.backButtonIcon
import io.ktor.util.Platform
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.left_arrow_svgrepo_com
import muktokowlomcmp.composeapp.generated.resources.notification_svgrepo_com
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
    onBackPress: () -> Unit
) {
    TopAppBar(
        title = {
            if (homeHeaderEnable) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(Res.drawable.user),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp).clip(shape = CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    WidthGap(15.dp)
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Sajib Roy",
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "sajibroy206@gmail.com",
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.4f
                                )
                            )
                        )
                    }
                }
            } else {
                Text(text = title)
            }

        },
        actions = {
            if (isActonButtonEnable) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(Res.drawable.search_alt_svgrepo_com),
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(Res.drawable.notification_svgrepo_com),
                        contentDescription = null
                    )
                }
            }

        },
        navigationIcon = {
            if (isBackButtonEnable) {
                IconButton(onClick = {
                    onBackPress.invoke()
                }) {
                    Icon(
                        imageVector = backButtonIcon(),
                        contentDescription = null
                    )
                }
            }

        },
    )
}

@Preview
@Composable
fun MyCustomAppBarPreview() {
    MyCustomAppBar(
        isBackButtonEnable = true,
        isActonButtonEnable = true,
        title = "Details 01",
        homeHeaderEnable = true,
        onBackPress = {}
    )
}