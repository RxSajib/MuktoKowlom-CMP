package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.unit.dp
import com.lt.compose_views.refresh_layout.RefreshContentStateEnum
import com.lt.compose_views.refresh_layout.RefreshLayout
import com.lt.compose_views.refresh_layout.rememberRefreshLayoutState
import com.lt.compose_views.touch_bar.BasicsProgressBar
import io.github.rhobus.kloading.animation.WatchRunningAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun Loader() {
    Box(modifier = Modifier.fillMaxWidth(1f).padding(5.dp), contentAlignment = Alignment.Center) {
        WatchRunningAnimation(
            clockColor = Color.Gray.copy(alpha = 0.1f),
            handColor = Color.Gray,
            clockSize = 30.dp
        )
    }
}