package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliad.model.Language
import com.aliad.muktokowlom.ui.theme.onPrimaryLight

@Composable
fun LanguageItem(data: Language, isSelected: Boolean, onItemSelect: (Language) -> Unit) {

    Box(
        modifier = Modifier
            .border(
                1.dp,  if (isSelected) onPrimaryLight else Color.Gray.copy(alpha = 0.5f)
                , shape = RoundedCornerShape(
                    8.dp
                )
            )
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .clickable {
                onItemSelect.invoke(data)
            }
            .fillMaxSize(), contentAlignment = Alignment.Center) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp)
                .padding(vertical = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = if (isSelected) onPrimaryLight.copy(alpha = 0.2f) else Color.Gray.copy(alpha = 0.3f)
                            , shape = RoundedCornerShape(6.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = data.symbol?: "en",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight(700), fontSize = 32.sp
                        ),
                        color = if (isSelected) onPrimaryLight else Color.Gray.copy(alpha = 0.5f)
                    )
                }

                WidthGap(12.dp)

                Column {
                    Text(
                        text = data.text?: "",
                        fontSize = 16.sp,
                        color = if (isSelected) onPrimaryLight else Color.Gray.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )


                    Text(
                        text = "(${data.description})",
                        fontSize = 14.sp,
                        color = if (isSelected) onPrimaryLight else Color.Gray.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }

            }



            MyRadioButton(
                isSelected = isSelected,
                onClick = {

                    onItemSelect.invoke(data)
                },
            )
        }

    }
}
