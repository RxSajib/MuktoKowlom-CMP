package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import coil3.Image
import com.aliad.model.SnackBarDetails
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class MySnackPosition {
    TOP,
    BOTTOM
}


@Composable
fun MyCustomNotifySnackBar(
    modifier: Modifier = Modifier.fillMaxSize(),
    message: String,
    //  leftIcon: ImageVector? = null,
    position: MySnackPosition = MySnackPosition.TOP,
    onDismiss: (() -> Unit)? = null
) {
    // visible resets when message changes
    var visible by remember(message) { mutableStateOf(true) }

    // animation duration (ms) — used to wait before calling onDismiss
    val animDuration = 300

    LaunchedEffect(message) {
        // auto-dismiss after 2s (visible -> false triggers exit animation)
        delay(3000)
        visible = false
        // wait for exit animation to finish before invoking onDismiss
        delay(animDuration.toLong())
        onDismiss?.invoke()
    }

    val alignment = when (position) {
        MySnackPosition.TOP -> Alignment.TopCenter
        MySnackPosition.BOTTOM -> Alignment.BottomCenter
    }

    val enterTransition = remember(position) {
        slideInVertically(
            // if TOP, come from above (-height); if BOTTOM, come from below (+height)
            initialOffsetY = { if (position == MySnackPosition.TOP) -it else it },
            animationSpec = tween(durationMillis = animDuration)
        )
    }

    val exitTransition = remember(position) {
        slideOutVertically(
            // if TOP, go back up (-height); if BOTTOM, go back down (+height)
            targetOffsetY = { if (position == MySnackPosition.TOP) -it else it },
            animationSpec = tween(durationMillis = animDuration)
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = alignment
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = enterTransition,
            exit = exitTransition
        ) {
            CustomSnackBar(
                message = message,
                //  leftIcon = leftIcon,
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = if (position == MySnackPosition.TOP) 50.dp else 16.dp
                    )
                    .wrapContentWidth()
            )
        }
    }
}


@Composable
fun CustomSnackBar(
    message: String,
    //   leftIcon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            Image(
                painter = painterResource(Res.drawable.muktokowlom),
                contentDescription = null,
                modifier = Modifier.size(25.dp).clip(shape = CircleShape)
            )


            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = message,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.width(12.dp))

            MyCustomLottieAnim(lottieFile = "files/success_check.lottie", size = 25.dp)
        }
    }
}
