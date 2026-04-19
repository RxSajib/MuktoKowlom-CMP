package com.aliad.muktokowlom.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.aliad.model.MyBookItem
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.utils.getStoryData
import com.aliad.muktokowlom.utils.getTitle
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import muktokowlomcmp.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyCustomBannerItem(myBookItem: MyBookItem, onclick: (myBookItem: MyBookItem) -> Unit) {

    val viewModel : DataStoreViewModel = koinViewModel()
    val selectLn = viewModel.getStringData(key = AppConstant.SELECT_LOCAL).collectAsStateWithLifecycle("en")

    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp).padding(horizontal = 16.dp).clickable {
            onclick.invoke(myBookItem)
        }.clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = myBookItem.completedImageUri,
            contentDescription = null,
            modifier = Modifier.weight(0.2f).aspectRatio(1f)
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            error = painterResource(Res.drawable.placeholder),
            placeholder = painterResource(Res.drawable.placeholder)
        )


        Column(modifier = Modifier.weight(1f).padding(start = 10.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = getTitle(selectLn = selectLn.value, title = myBookItem.titleEn, titleBn = myBookItem.titleBn) ,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,

                    )

                Text(
                    text = getStoryData(
                        dataBn = myBookItem.summaryBn ?: "",
                        dataEn = myBookItem.summaryEn ?: ""
                    ) ,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.inverseSurface,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 4,
                )
            }
            HeightGap(height = 10.dp)
            MyRatingBar(
                rating = myBookItem.ratingToInt?.toFloat() ?: 0f,
                starSize = 15.dp,
                onStarClick = {},
                isIndicator = true
            )
        }


    }
}

