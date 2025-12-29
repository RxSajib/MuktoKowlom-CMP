package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StoryItem(){
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(150.dp),
            model = "sdds",
            contentDescription = null
        )
        HeightGap(10.dp)
        Text(
            text = "Story title",
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Author name", modifier = Modifier.fillMaxWidth())

    }
}

@Composable
@Preview
fun StoryItemPreview(){
    StoryItem()
}