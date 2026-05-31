package com.aliad.muktokowlom.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.LocalPlatformContext
import com.aliad.model.MyCategory
import com.aliad.muktokowlom.ui.component.CategoryDialogItem
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.MyCustomButton
import com.aliad.muktokowlom.ui.component.WidthGap
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.presentation.signIn.ui.uploadStories.UploadStoriesViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.cancel
import muktokowlomcmp.composeapp.generated.resources.fill_in_the_details_below_to_submit_your_story
import muktokowlomcmp.composeapp.generated.resources.select
import muktokowlomcmp.composeapp.generated.resources.share_your_story
import org.jetbrains.compose.resources.stringResource

@Composable
fun CategoryDialog(
    onDismissRequest: () -> Unit,
    onSelected: (MyCategory) -> Unit,
    value: List<MyCategory>,
    viewModel: UploadStoriesViewModel
) {
    val contextCoil = LocalPlatformContext.current

    Dialog(onDismissRequest = { onDismissRequest.invoke() }) {
        Column(
            modifier = Modifier.fillMaxWidth().clip(shape = RoundedCornerShape(16.dp))
                .background(color = MaterialTheme.colorScheme.inversePrimary).padding(10.dp)
        ) {
            Text(
                text = stringResource(Res.string.share_your_story),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(Res.string.fill_in_the_details_below_to_submit_your_story),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = adjustedFontSize(10.0f),

                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            HeightGap(height = 20.dp)
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(value.size) { position ->
                    CategoryDialogItem(context = contextCoil, category = value[position], onClick = {
                        viewModel.apply {
                            selectedCategoryPosition = position
                            selectedCategory = value[position]
                        }
                    }, isSelected = viewModel.selectedCategoryPosition == position)
                }
            }
            HeightGap(height = 20.dp)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                MyCustomButton(
                    title = stringResource(Res.string.cancel),
                    modifier = Modifier.weight(1f),
                    onClickButton = {
                        onDismissRequest.invoke()
                    },
                    isEnable = true,
                    padding = 0.dp
                )
                WidthGap(width = 20.dp)
                MyCustomButton(
                    isEnable = viewModel.selectedCategoryPosition != -1,
                    backgroundColor = Color.Red,
                    title = stringResource(Res.string.select),
                    modifier = Modifier.weight(1f),
                    onClickButton = {
                        onSelected.invoke(
                            viewModel.selectedCategory
                        )
                    },
                    padding = 0.dp
                )
            }
        }
    }
}