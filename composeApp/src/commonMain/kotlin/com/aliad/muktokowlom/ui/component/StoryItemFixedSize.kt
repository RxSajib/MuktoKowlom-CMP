package com.aliad.muktokowlom.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.aliad.model.MyBookItem
import com.aliad.muktokowlom.data.app_constant.AppConstant
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.utils.getTitle
import com.aliad.presentation.signIn.ui.datastore.DataStoreViewModel
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StoryItemFixedSize(selectLn : String, item: MyBookItem?, context : PlatformContext, onClick: (MyBookItem) -> Unit){

    Column(modifier = Modifier.width(220.dp).clip(shape = RoundedCornerShape(10.dp)).clickable{
        onClick.invoke(item?: MyBookItem())
    }.padding(10.dp)) {
        Box{
            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                model = ImageRequest.Builder(context).data(item?.completedImageUri ?: "").size(500).build() ,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.placeholder),
                error = painterResource(Res.drawable.placeholder)
            )
            if(item?.isPaidStory == true){
                DiagonalCornerView()
            }

        }

        HeightGap(10.dp)
        MyRatingBar(
            rating = item?.ratingToInt?.toFloat() ?: 0f,
            starSize = 15.dp,
            onStarClick = {},
            isIndicator = true
        )
        HeightGap(2.dp)
        Text(
            text = getTitle(selectLn = selectLn, title = item?.titleEn, titleBn = item?.titleBn),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = item?.authorName ?: "Unknow Author", modifier = Modifier.fillMaxWidth(),
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f),
                fontSize = adjustedFontSize(10f)
            )
        )

    }

}