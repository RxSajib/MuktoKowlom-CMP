package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SearchKeywordItem(keywordName: String, onClick: () -> Unit) {
    Text(
        text = keywordName,
        style = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            fontSize = adjustedFontSize(10f)
        ),
        modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            .clip(shape = CircleShape).clickable{
                onClick.invoke()
            }
            .padding(10.dp)
    )
}

@Composable
@Preview
fun SearchKeywordItemPreview() {
    SearchKeywordItem(keywordName = "Jon Walker") {}
}