package com.aliad.muktokowlom.ui.upload_stories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.component.HeightGap
import com.aliad.muktokowlom.ui.component.MyCustomAppBar
import com.aliad.muktokowlom.ui.component.MyCustomButton
import com.aliad.muktokowlom.ui.component.MySecondaryCustomInputFiled
import com.aliad.muktokowlom.ui.component.MySubscriptionButton
import com.aliad.muktokowlom.ui.component.MyTagInputField
import com.aliad.muktokowlom.ui.component.StoryFileUploadCustomButton
import com.aliad.muktokowlom.ui.component.StoryUploadCustomHeader
import com.aliad.muktokowlom.ui.component.WheelDatePickerDialog
import com.aliad.muktokowlom.ui.component.WidthGap
import com.aliad.muktokowlom.ui.dialog.CategoryDialog
import com.aliad.muktokowlom.ui.theme.adjustedFontSize
import com.aliad.muktokowlom.ui.theme.green
import com.aliad.muktokowlom.ui.theme.onPrimaryLight
import com.aliad.presentation.signIn.ui.uploadStories.UploadStoriesViewModel
import com.aliad.utils.MyCustomLogger
import io.github.ismoy.imagepickerkmp.domain.config.CropConfig
import io.github.ismoy.imagepickerkmp.domain.config.GalleryConfig
import io.github.ismoy.imagepickerkmp.domain.config.UiConfig
import io.github.ismoy.imagepickerkmp.domain.extensions.loadBytes
import io.github.ismoy.imagepickerkmp.domain.models.MimeType
import io.github.ismoy.imagepickerkmp.features.imagepicker.config.ImagePickerKMPConfig
import io.github.ismoy.imagepickerkmp.features.imagepicker.model.ImagePickerResult
import io.github.ismoy.imagepickerkmp.features.imagepicker.ui.rememberImagePickerKMP
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.add_tags
import muktokowlomcmp.composeapp.generated.resources.below
import muktokowlomcmp.composeapp.generated.resources.category
import muktokowlomcmp.composeapp.generated.resources.dashboard
import muktokowlomcmp.composeapp.generated.resources.fill_in_the_details_below_to_submit_your_story
import muktokowlomcmp.composeapp.generated.resources.free
import muktokowlomcmp.composeapp.generated.resources.free_details
import muktokowlomcmp.composeapp.generated.resources.icon_arrow_down
import muktokowlomcmp.composeapp.generated.resources.icon_bookmark
import muktokowlomcmp.composeapp.generated.resources.icon_calender
import muktokowlomcmp.composeapp.generated.resources.icon_crown
import muktokowlomcmp.composeapp.generated.resources.icon_crown_dark
import muktokowlomcmp.composeapp.generated.resources.icon_file
import muktokowlomcmp.composeapp.generated.resources.icon_gallery
import muktokowlomcmp.composeapp.generated.resources.icon_lock
import muktokowlomcmp.composeapp.generated.resources.icon_pen
import muktokowlomcmp.composeapp.generated.resources.icon_send
import muktokowlomcmp.composeapp.generated.resources.icon_tag
import muktokowlomcmp.composeapp.generated.resources.icon_users
import muktokowlomcmp.composeapp.generated.resources.icon_world
import muktokowlomcmp.composeapp.generated.resources.icon_world_dark
import muktokowlomcmp.composeapp.generated.resources.or
import muktokowlomcmp.composeapp.generated.resources.premium
import muktokowlomcmp.composeapp.generated.resources.premium_details_two
import muktokowlomcmp.composeapp.generated.resources.published_date
import muktokowlomcmp.composeapp.generated.resources.select_category
import muktokowlomcmp.composeapp.generated.resources.select_date
import muktokowlomcmp.composeapp.generated.resources.share_your_story
import muktokowlomcmp.composeapp.generated.resources.start_writing_your_story
import muktokowlomcmp.composeapp.generated.resources.story_summary_max_word
import muktokowlomcmp.composeapp.generated.resources.story_title
import muktokowlomcmp.composeapp.generated.resources.tags
import muktokowlomcmp.composeapp.generated.resources.title
import muktokowlomcmp.composeapp.generated.resources.upload_file
import muktokowlomcmp.composeapp.generated.resources.upload_stories
import muktokowlomcmp.composeapp.generated.resources.upload_story_cover
import muktokowlomcmp.composeapp.generated.resources.upload_story_cover_type
import muktokowlomcmp.composeapp.generated.resources.upload_story_file
import muktokowlomcmp.composeapp.generated.resources.upload_story_file_type
import muktokowlomcmp.composeapp.generated.resources.what_do_you_want_for_readers
import muktokowlomcmp.composeapp.generated.resources.write_full_story_here
import muktokowlomcmp.composeapp.generated.resources.write_short_summary_of_your_story
import muktokowlomcmp.composeapp.generated.resources.your_data_is_safe_and_secure_we_respect_your_privacy
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

private const val TAG = "UploadStoriesScreen"

@Composable
fun UploadStoriesScreen(backStack: NavBackStack<NavKey>, rootBackStack: NavBackStack<NavKey>) {

    val viewModel: UploadStoriesViewModel = koinViewModel()
    val storyTitle = viewModel.storyTitleFlow.collectAsStateWithLifecycle()
    val category = viewModel.categoryFlow.collectAsStateWithLifecycle()
    val publishedDate = viewModel.publishedDateFlow.collectAsStateWithLifecycle()
    val storySummary = viewModel.storySummaryFlow.collectAsStateWithLifecycle()
    val fullStory = viewModel.fullStoryFlow.collectAsStateWithLifecycle()
    val categoryData = viewModel.categoryData.collectAsStateWithLifecycle()
    val imageFileName = viewModel.storyImageNameFlow.collectAsStateWithLifecycle()
    val pdfFileName = viewModel.storyFileNameFlow.collectAsStateWithLifecycle()
    val tags = viewModel.tagsFlow.collectAsStateWithLifecycle(emptyList())
    val isEnableUploadButton = viewModel.isEnableButton.collectAsStateWithLifecycle(false)


    val imagePicker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            galleryConfig = GalleryConfig(
                allowMultiple = false,
            ),
            cropConfig = CropConfig(enabled = true, circularCrop = false),
            uiConfig = UiConfig()
        )
    )

    val pdfPicker = rememberImagePickerKMP(
        config = ImagePickerKMPConfig(
            galleryConfig = GalleryConfig(
                allowMultiple = false,
            ),
            uiConfig = UiConfig()
        )
    )

    LaunchedEffect(pdfPicker.result) {
        when (pdfPicker.result) {
            is ImagePickerResult.Success -> {
                val imageFileName =
                    (pdfPicker.result as ImagePickerResult.Success).first?.fileName ?: ""
                val imageSize = (pdfPicker.result as ImagePickerResult.Success).first?.fileSize ?: 0

                viewModel.apply {
                    saveStoryFileName(fileName = imageFileName)
                    saveStorySize(fileSize = imageSize)
                }
            }

            is ImagePickerResult.Error -> {

            }

            is ImagePickerResult.Loading -> {

            }

            is ImagePickerResult.Dismissed -> {

            }

            is ImagePickerResult.Idle -> {

            }
        }
    }

    LaunchedEffect(imagePicker.result) {
        when (imagePicker.result) {
            is ImagePickerResult.Success -> {
                val imageFileName =
                    (imagePicker.result as ImagePickerResult.Success).first?.fileName ?: ""
                val imageSize =
                    (imagePicker.result as ImagePickerResult.Success).first?.fileSize ?: 0

                viewModel.apply {
                    saveStoryImage(imageName = imageFileName)
                    saveStorySize(fileSize = imageSize)
                }
            }

            is ImagePickerResult.Error -> {

            }

            is ImagePickerResult.Loading -> {

            }

            is ImagePickerResult.Dismissed -> {

            }

            is ImagePickerResult.Idle -> {

            }
        }
    }


    LaunchedEffect(viewModel.saveCategory) {
        if (viewModel.saveCategory.name.isNotBlank()) {
            viewModel.inputCategory(category = viewModel.saveCategory.name)
        }
    }

    Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {

        Scaffold(
            topBar = {
                MyCustomAppBar(
                    onBackPress = {
                        try {
                            if (backStack.size > 1) {
                                backStack.removeLastOrNull()
                            } else {
                                rootBackStack.removeLastOrNull()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    },
                    title = stringResource(Res.string.upload_stories),
                    editProfile = {})
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface).padding(innerPadding)
                    .padding(16.dp).imePadding()
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                        .verticalScroll(state = rememberScrollState())
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

                    StoryUploadCustomHeader(
                        icon = painterResource(Res.drawable.dashboard),
                        title = stringResource(Res.string.title)
                    )
                    HeightGap(height = 10.dp)
                    MySecondaryCustomInputFiled(
                        placeholder = stringResource(Res.string.story_title),
                        inputData = storyTitle.value,
                        modifier = Modifier,
                        onValueChanged = { title ->
                            viewModel.inputTitle(title = title)
                        }
                    )
                    HeightGap(height = 15.dp)
                    StoryUploadCustomHeader(
                        icon = painterResource(Res.drawable.icon_bookmark),
                        title = stringResource(Res.string.category)
                    )
                    HeightGap(height = 10.dp)
                    MySecondaryCustomInputFiled(
                        placeholder = stringResource(Res.string.select_category),
                        modifier = Modifier,
                        inputData = category.value,
                        onValueChanged = { category ->
                            viewModel.inputCategory(category = category)
                        },
                        readOnly = true,
                        tralingIcon = painterResource(Res.drawable.icon_arrow_down),
                        onClick = {
                            viewModel.showCategoryDialog = true
                        }
                    )
                    HeightGap(height = 15.dp)
                    StoryUploadCustomHeader(
                        icon = painterResource(Res.drawable.icon_calender),
                        title = stringResource(Res.string.published_date)
                    )
                    HeightGap(height = 10.dp)
                    MySecondaryCustomInputFiled(
                        placeholder = stringResource(Res.string.select_date),
                        modifier = Modifier,
                        tralingIcon = painterResource(Res.drawable.icon_calender),
                        inputData = publishedDate.value,
                        readOnly = true,
                        onValueChanged = { publishedDate ->
                            viewModel.inputPublishedDate(publishedDate = publishedDate)
                        },
                        onClick = {
                            viewModel.showCalender = true
                        }
                    )
                    HeightGap(height = 15.dp)
                    StoryUploadCustomHeader(
                        icon = painterResource(Res.drawable.icon_tag),
                        title = stringResource(Res.string.tags)
                    )

                    HeightGap(height = 10.dp)

                    key(tags.value) {
                        Column(
                            modifier = Modifier.fillMaxWidth().border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                        ) {
                            MyTagInputField(
                                tags = tags.value,
                                onTagAdded = { tag ->
                                    viewModel.inputTags(tag)
                                },
                                onTagRemoved = { tag ->
                                    viewModel.removeTag(tag = tag)
                                }
                            )

                        }

                    }


                    HeightGap(height = 15.dp)
                    StoryUploadCustomHeader(
                        icon = painterResource(Res.drawable.icon_file),
                        title = stringResource(Res.string.story_summary_max_word)
                    )

                    HeightGap(height = 10.dp)
                    MySecondaryCustomInputFiled(
                        placeholder = stringResource(Res.string.write_short_summary_of_your_story),
                        modifier = Modifier.height(100.dp),
                        inputData = storySummary.value,
                        onValueChanged = { summary ->
                            viewModel.inputStorySummary(storySummary = summary)
                        }
                    )
                    HeightGap(height = 15.dp)
                    StoryUploadCustomHeader(
                        icon = painterResource(Res.drawable.icon_pen),
                        title = stringResource(Res.string.write_full_story_here)
                    )

                    HeightGap(height = 10.dp)
                    MySecondaryCustomInputFiled(
                        placeholder = stringResource(Res.string.start_writing_your_story),
                        modifier = Modifier.height(100.dp),
                        inputData = fullStory.value,
                        onValueChanged = { fullStory ->
                            viewModel.inputFullStoryDetails(fullStoryDetails = fullStory)
                        }
                    )

                    HeightGap(height = 15.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        HorizontalDivider(
                            modifier = Modifier.weight(1f).height(1.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.inverseSurface.copy(
                                        alpha = 0.3f
                                    )
                                )
                        )
                        WidthGap(width = 15.dp)
                        Text(
                            buildAnnotatedString {
                                append(stringResource(Res.string.or))

                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xFF3B82F6),
                                        fontWeight = FontWeight.Medium
                                    )
                                ) {
                                    append(" " + stringResource(Res.string.upload_file))
                                }

                                append(" " + stringResource(Res.string.below))
                            },
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = adjustedFontSize(8.0f)
                            ),
                            color = Color.Gray // default text color
                        )
                        WidthGap(width = 15.dp)
                        HorizontalDivider(
                            modifier = Modifier.weight(1f).height(1.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.inverseSurface.copy(
                                        alpha = 0.3f
                                    )
                                )
                        )
                    }

                    HeightGap(height = 10.dp)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        StoryFileUploadCustomButton(
                            title = stringResource(Res.string.upload_story_file),
                            fileType = stringResource(Res.string.upload_story_file_type),
                            fileIcon = painterResource(Res.drawable.icon_file),
                            fileButtonBgColor = onPrimaryLight,
                            modifier = Modifier.weight(1f),
                            fileName = pdfFileName.value,
                            onClickRemove = {
                                viewModel.removeStoryFileName()
                            }
                        ) {
                            pdfPicker.launchGallery(mimeTypes = listOf(MimeType.APPLICATION_PDF))
                        }

                        WidthGap(width = 10.dp)

                        StoryFileUploadCustomButton(
                            title = stringResource(Res.string.upload_story_cover),
                            fileType = stringResource(Res.string.upload_story_cover_type),
                            fileIcon = painterResource(Res.drawable.icon_gallery),
                            fileButtonBgColor = green,
                            fileName = imageFileName.value,
                            modifier = Modifier.weight(1f),
                            onClickRemove = {
                                viewModel.removeStoryCoverImageName()
                            }
                        ) {
                            imagePicker.launchGallery(mimeTypes = listOf(MimeType.IMAGE_JPEG))
                        }
                    }

                    HeightGap(height = 10.dp)

                    StoryUploadCustomHeader(
                        icon = painterResource(Res.drawable.icon_users),
                        title = stringResource(Res.string.what_do_you_want_for_readers)
                    )

                    HeightGap(height = 10.dp)

                    Row(modifier = Modifier.fillMaxWidth()) {
                        MySubscriptionButton(
                            rightImage = if (!isSystemInDarkTheme()) painterResource(Res.drawable.icon_world) else painterResource(
                                Res.drawable.icon_world_dark
                            ),
                            title = stringResource(Res.string.free),
                            details = stringResource(Res.string.free_details),
                            isSelected = viewModel.selectStoryIsFree,
                            modifier = Modifier.weight(1f)
                        ) {
                            viewModel.selectStoryIsFree = true
                        }

                        WidthGap(width = 10.dp)

                        MySubscriptionButton(
                            rightImage = if (!isSystemInDarkTheme()) painterResource(Res.drawable.icon_crown) else painterResource(
                                Res.drawable.icon_crown_dark
                            ),
                            title = stringResource(Res.string.premium),
                            details = stringResource(Res.string.premium_details_two),
                            isSelected = !viewModel.selectStoryIsFree,
                            modifier = Modifier.weight(1f)
                        ) {
                            viewModel.selectStoryIsFree = false
                        }
                    }

                }
                HeightGap(height = 15.dp)

                MyCustomButton(
                    isEnable = isEnableUploadButton.value,
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(Res.string.upload_stories),
                    leftIcon = painterResource(Res.drawable.icon_send),
                    onClickButton = {

                    })
                HeightGap(height = 10.dp)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.icon_lock),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        colorFilter = ColorFilter.tint(
                            color = MaterialTheme.colorScheme.inverseSurface.copy(
                                alpha = 0.5f
                            )
                        )
                    )
                    WidthGap(width = 5.dp)
                    Text(
                        text = stringResource(Res.string.your_data_is_safe_and_secure_we_respect_your_privacy),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = adjustedFontSize(8.0f),
                            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)
                        )
                    )
                }
            }
        }

        if (viewModel.showCalender) {
            WheelDatePickerDialog(
                onDismissRequest = {
                    viewModel.showCalender = false
                },
                onDateSelected = { localDate ->
                    viewModel.showCalender = false
                    viewModel.inputPublishedDate(publishedDate = localDate.toString())
                }
            )
        }

        if (viewModel.showCategoryDialog) {
            CategoryDialog(onDismissRequest = {
                viewModel.showCategoryDialog = false

            }, onSelected = { myCategory ->
                viewModel.saveCategory = myCategory
                viewModel.showCategoryDialog = false
            }, value = categoryData.value, viewModel = viewModel)
        }
    }
}