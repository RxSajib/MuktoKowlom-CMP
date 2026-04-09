package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StoryLoaderShimmer() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        repeat(20) {
            StoryShimmerRow()
        }
    }
}

@Composable
@Preview
fun StoryLoaderShimmerPreview() {
    StoryLoaderShimmer()
}