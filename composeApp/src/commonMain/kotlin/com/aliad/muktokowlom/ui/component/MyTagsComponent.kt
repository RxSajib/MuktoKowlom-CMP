package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.aliad.muktokowlom.ui.theme.MuktoKowlomTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource

@Composable
fun MyTagsComponent(title: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clip(shape = CircleShape).background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = adjustedFontSize(10f),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )
        WidthGap(width = 4.dp)
        IconButton(
            onClick = { onClick.invoke() },
            modifier = Modifier.size(16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier.size(10.dp)
            )
        }
    }
}

@Composable
fun MyTagInputField(
    tags: List<String>,
    onTagAdded: (String) -> Unit,
    onTagRemoved: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Add tag..."
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (tags.isNotEmpty()) {
            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tags.forEach { tag ->
                    MyTagsComponent(
                        title = tag,
                        onClick = { onTagRemoved(tag) }
                    )
                }
            }
        }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        fontSize = adjustedFontSize(12f)
                    )
                )
            },
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledTextColor = Color(0xff1E1C21),
                disabledPlaceholderColor = Color(0xff514D57)
            ),
            textStyle = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.primary,
                fontSize = adjustedFontSize(12f)
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.isNotBlank()) {
                        onTagAdded(text.trim())
                        text = ""
                    }
                }
            ),
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun MyTagInputFieldPreview() {
    var tags by remember { mutableStateOf(listOf("Compose", "Kotlin", "Android")) }
    MuktoKowlomTheme {

          //  Text("Tag Input Field Example:", style = MaterialTheme.typography.titleMedium)
            MyTagInputField(
                tags = tags,
                onTagAdded = { tags = tags + it },
                onTagRemoved = { tag -> tags = tags.filter { it != tag } }
            )
        }
}
