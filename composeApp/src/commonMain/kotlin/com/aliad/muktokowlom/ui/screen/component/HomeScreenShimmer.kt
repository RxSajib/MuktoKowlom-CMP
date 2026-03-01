package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lt.compose_views.value_selector.rememberValueSelectState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreenShimmer(){
    Column(modifier = Modifier.fillMaxSize().verticalScroll(state = rememberScrollState()).padding(16.dp)) {
        ShimmerBox()
        HeightGap(height = 16.dp)
        StoryShimmerRow()
        HeightGap(height = 16.dp)
        ShimmerBox()
        HeightGap(height = 16.dp)
        StoryShimmerRow()
        HeightGap(height = 16.dp)
        ShimmerBox()
        HeightGap(height = 16.dp)
        StoryShimmerRow()
        HeightGap(height = 16.dp)
        ShimmerBox()
        HeightGap(height = 16.dp)
        StoryShimmerRow()
    }
}

@Composable
@Preview
fun HomeScreenShimmerPreview(){
    HomeScreenShimmer()
}