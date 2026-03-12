package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchKeywordItem(keywordName: String) {
    Text(
        text = keywordName,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.inverseOnSurface
        ),
        modifier = Modifier.padding(10.dp)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)
            
    )
}

@Composable
@Preview
fun SearchKeywordItemPreview() {
    SearchKeywordItem(keywordName = "Jon Walker")
}