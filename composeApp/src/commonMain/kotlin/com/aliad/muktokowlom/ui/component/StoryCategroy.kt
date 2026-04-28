package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.most_popular
import muktokowlomcmp.composeapp.generated.resources.view_all
import org.jetbrains.compose.resources.stringResource

@Composable
fun StoryCategoryWithAllButton(categoryTitle : String, onClick: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = categoryTitle,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        TextButton(
           onClick = {onClick.invoke()}
        ){
            Text(
                text = stringResource(Res.string.view_all),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = adjustedFontSize(10.0f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                )
            )
        }
    }
}