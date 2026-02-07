package com.aliad.muktokowlom.ui.screen.storyDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.AsyncImage
import com.aliad.model.MyBookItem
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.screen.component.MyCustomButton
import com.aliad.muktokowlom.ui.screen.component.QuickAccessButton
import com.aliad.muktokowlom.ui.screen.component.WidthGap
import com.aliad.muktokowlom.ui.screen.component.WriterInfo
import com.aliad.presentation.signIn.ui.storyDetails.StoryDetailsViewModel
import com.eygraber.seymour.SeymourText
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.about_book
import muktokowlomcmp.composeapp.generated.resources.add_favorites
import muktokowlomcmp.composeapp.generated.resources.all_release
import muktokowlomcmp.composeapp.generated.resources.category
import muktokowlomcmp.composeapp.generated.resources.dashboard
import muktokowlomcmp.composeapp.generated.resources.favorite_disable
import muktokowlomcmp.composeapp.generated.resources.favorite_enable
import muktokowlomcmp.composeapp.generated.resources.list
import muktokowlomcmp.composeapp.generated.resources.placeholder
import muktokowlomcmp.composeapp.generated.resources.published
import muktokowlomcmp.composeapp.generated.resources.rating
import muktokowlomcmp.composeapp.generated.resources.read_full_story
import muktokowlomcmp.composeapp.generated.resources.sampleText
import muktokowlomcmp.composeapp.generated.resources.see_less
import muktokowlomcmp.composeapp.generated.resources.see_more
import muktokowlomcmp.composeapp.generated.resources.send_rating
import muktokowlomcmp.composeapp.generated.resources.sign_in_account
import muktokowlomcmp.composeapp.generated.resources.similar_story
import muktokowlomcmp.composeapp.generated.resources.views
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StoryDetailsScreen(myBookItem: MyBookItem, backStack: NavBackStack<NavKey>) {

    val viewModel : StoryDetailsViewModel = koinViewModel()

    Scaffold(
        topBar = {
            MyCustomAppBar(title = myBookItem.titleBn ?: "Unknown", onBackPress = {
                backStack.remove(AppDestination.StoryDetails(myBookItem = myBookItem))
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeightGap(height = 15.dp)
            AsyncImage(
                model = myBookItem.completedImageUri,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(Res.drawable.placeholder),
                error = painterResource(Res.drawable.placeholder),
                modifier = Modifier.fillMaxWidth(.5f).aspectRatio(2f)
                    .clip(shape = RoundedCornerShape(10.dp))
            )
            HeightGap(height = 15.dp)
            Text(
                text = myBookItem.titleBn ?: " Unknown Title",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,

                )
            HeightGap(height = 10.dp)
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MyCustomButton(
                    title = stringResource(Res.string.send_rating),
                    modifier = Modifier.weight(1f),
                    backgroundColor = MaterialTheme.colorScheme.inverseSurface,
                    onClickButton = {

                    },
                    padding = 0.dp
                )
                WidthGap(width = 20.dp)
                MyCustomButton(
                    title = stringResource(Res.string.read_full_story),
                    modifier = Modifier.weight(1f),
                    onClickButton = {

                    },
                    padding = 0.dp
                )
            }
            HeightGap(height = 10.dp)
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "2026-02-05",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(Res.string.published),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "0",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(Res.string.views),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "0",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(Res.string.rating),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            HeightGap(height = 10.dp)
            WriterInfo(
                modifier = Modifier.padding(horizontal = 16.dp),
                writerName = "Sajib Roy",
                profileImage = ""
            ) {

            }
            HeightGap(height = 10.dp)
            Text(
                text = stringResource(Res.string.about_book),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W500
                )
            )
            HeightGap(height = 5.dp)
            SeymourText(
                onSeeMoreChange = { viewModel.isExpandedText = it },
                isSeeMoreExpanded = viewModel.isExpandedText,
                text = stringResource(Res.string.sampleText),
                seeMoreText = stringResource(Res.string.see_more),
                seeLessText = stringResource(Res.string.see_less),
                seeMoreMaxLines = 3,
                seeLessMaxLines = Int.MAX_VALUE,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            )
            HeightGap(height = 10.dp)
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                QuickAccessButton(
                    modifier = Modifier.weight(1f).clip(shape = CircleShape).clickable{

                    },
                    icon = painterResource(Res.drawable.favorite_disable),
                    title = stringResource(Res.string.add_favorites)
                )
                WidthGap(width = 5.dp)
                QuickAccessButton(
                    modifier = Modifier.weight(1f).clip(shape = CircleShape).clickable{

                    },
                    icon = painterResource(Res.drawable.list),
                    title = stringResource(Res.string.category)
                )
                WidthGap(width = 5.dp)
                QuickAccessButton(
                    modifier = Modifier.weight(1f).clip(shape = CircleShape).clickable{

                    },
                    icon = painterResource(Res.drawable.dashboard),
                    title = stringResource(Res.string.all_release)
                )
            }
            HeightGap(height = 10.dp)
            Text(
                text = stringResource(Res.string.similar_story),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W500
                )
            )
            HeightGap(height = 5.dp)
        }
    }
}