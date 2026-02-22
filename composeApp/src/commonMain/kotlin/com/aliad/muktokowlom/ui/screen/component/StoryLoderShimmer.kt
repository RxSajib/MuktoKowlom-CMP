package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StoryLoaderShimmer(){
    Column(modifier = Modifier.fillMaxSize()) {
        repeat(20){
            StoryShimmerRow()
        }
    }
}

@Composable
@Preview
fun StoryLoaderShimmerPreview(){
    StoryLoaderShimmer()
}