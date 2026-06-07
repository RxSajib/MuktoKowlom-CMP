package com.aliad.presentation.signIn.ui.uploadStories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Path.Companion.combine
import androidx.compose.ui.text.style.TextDecoration.Companion.combine
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.GenericResponse
import com.aliad.model.MyCategory
import com.aliad.model.UploadStory
import com.aliad.model.User
import com.aliad.presentation.utils.MyCustomLogger
import com.aliad.usecase.CategoryUseCase
import com.aliad.usecase.UploadStoryUseCase
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private const val TAG = "UploadStoriesViewModel"

class UploadStoriesViewModel constructor(
    val categoryUseCase: CategoryUseCase,
    val uploadStoryUseCase: UploadStoryUseCase,
    val getStringData: GetStringData
) : ViewModel() {

   val selectedLocal = flow {
       emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
   }

    var data = MutableSharedFlow<GenericResponse<UploadStory>>()
    var isLoading by mutableStateOf(false)
    var storyCoverImage : ByteArray?= null
    var storyPdfFile : ByteArray?= null

    val tagsList: ArrayList<String> = ArrayList()
    var categoryID by mutableStateOf("")

    var selectedCategory by mutableStateOf(MyCategory())
    var saveCategory by mutableStateOf(MyCategory())


    var showCategoryDialog by mutableStateOf(false)
    var showCalender by mutableStateOf(false)
    var selectStoryIsFree by mutableStateOf(false)

    private var storyTitleMutableStateFlow = MutableStateFlow<String>("")
    val storyTitleFlow = storyTitleMutableStateFlow.asStateFlow()

    private var categoryMutableStateFlow = MutableStateFlow<String>("")
    val categoryFlow = categoryMutableStateFlow.asStateFlow()

    private var publishedDateMutableStateFlow = MutableStateFlow<String>("")
    val publishedDateFlow = publishedDateMutableStateFlow.asStateFlow()

    private var tagsMutableStateFlow = MutableStateFlow<List<String>>(emptyList())
    val tagsFlow = tagsMutableStateFlow.asStateFlow()

    private var storySummaryMutableStateFlow = MutableStateFlow<String>("")
    val storySummaryFlow = storySummaryMutableStateFlow.asStateFlow()

    private var fullStoryMutableStateFlow = MutableStateFlow<String>("")
    val fullStoryFlow = fullStoryMutableStateFlow.asStateFlow()

    private val storyImageNameMutableStateFlow = MutableStateFlow<String?>(null)
    val storyImageNameFlow = storyImageNameMutableStateFlow.asStateFlow()

    private val storyFileNameMutableStateFlow = MutableStateFlow<String?>(null)
    val storyFileNameFlow = storyFileNameMutableStateFlow.asStateFlow()

    private val storyFileSizeMutableStateFlow = MutableStateFlow<Double>(0.0)
    val storyFileSizeFlow = storyFileSizeMutableStateFlow.asStateFlow()

    fun inputTitle(title: String) {
        viewModelScope.launch {
            storyTitleMutableStateFlow.emit(title)
        }
    }

    fun inputCategory(category: String) {
        viewModelScope.launch {
            categoryMutableStateFlow.emit(category)
        }
    }

    fun inputPublishedDate(publishedDate: String) {
        viewModelScope.launch {
            publishedDateMutableStateFlow.emit(publishedDate)
        }
    }

    fun inputStorySummary(storySummary: String) {
        viewModelScope.launch {
            storySummaryMutableStateFlow.emit(storySummary)
        }
    }

    fun inputFullStoryDetails(fullStoryDetails: String) {
        viewModelScope.launch {
            fullStoryMutableStateFlow.emit(fullStoryDetails)
        }
    }

    fun inputTags(tags: String) {
        viewModelScope.launch {
            tagsList.add(tags)
            tagsMutableStateFlow.emit(tagsList)
        }
    }

    fun removeTag(tag: String) {
        viewModelScope.launch {
            tagsList.remove(tag)
            MyCustomLogger.logInfo(tag = TAG, message = "tag remove $tag ${tagsList.toString()}")
        }
    }

    fun saveStoryImage(imageName: String) {
        viewModelScope.launch {
            storyImageNameMutableStateFlow.emit(imageName)
        }
    }

    fun removeStoryCoverImageName() {
        viewModelScope.launch {
            storyImageNameMutableStateFlow.emit(null)
        }
    }

    fun saveStoryFileName(fileName: String) {
        viewModelScope.launch {
            storyFileNameMutableStateFlow.emit(fileName)
        }
    }

    fun saveStorySize(fileSize: Long) {
        viewModelScope.launch {
            val mb = fileSize / (1024.0 * 1024.0)
            val rounded = (mb * 100).toInt() / 100.0
        }
    }

    fun removeStoryFileName() {
        viewModelScope.launch {
            storyFileNameMutableStateFlow.emit(null)
            storyFileSizeMutableStateFlow.emit(0.0)
        }
    }

    private var getCategoryMutableStateFlow = MutableStateFlow<List<MyCategory>>(emptyList())
    val categoryData = getCategoryMutableStateFlow.asStateFlow()

    var selectedCategoryPosition by mutableStateOf(-1)

    init {
        getCategory()
    }

    private fun getCategory() {
        viewModelScope.launch {
            val response = categoryUseCase.getCategory()
            when (response) {
                is ApiResult.Success -> {
                    getCategoryMutableStateFlow.emit(response.data)
                }

                is ApiResult.Error -> {
                    getCategoryMutableStateFlow.emit(emptyList())
                }
            }
        }
    }



    val isEnableButton = combine(
            flow = storyTitleFlow,
            flow2 = categoryFlow,
            flow3 = publishedDateFlow,
            flow4 = tagsFlow,
            flow5 = storySummaryFlow,
    ) { storyTitle, category, dateOfPublished, tags, storySummary ->
        storyTitle.isNotBlank() &&
                category.isNotBlank() &&
                dateOfPublished.isNotBlank() &&
                tags.isNotEmpty() &&
                storySummary.isNotBlank()

    }.combine(storyImageNameFlow){  isValid, imageName  ->
        isValid && !imageName.isNullOrEmpty()
    }


    fun uploadStory(){
        viewModelScope.launch {
            isLoading = true
         val response =   uploadStoryUseCase.uploadStory(
                titleBn = storyTitleMutableStateFlow.value,
                categoryID = categoryID,
                tagsBn = tagsList,
                publishedDate = publishedDateMutableStateFlow.value,
                summaryBn = storySummaryMutableStateFlow.value,
                imageFile = storyCoverImage!!,
                storyFileBn = storyPdfFile, // 0 means free
                isPayable = "0",
                storyBn = fullStoryMutableStateFlow.value
            )
            isLoading = false

            when(response){
                is ApiResult.Success -> {
                    MyCustomLogger.logInfo(tag = TAG, message = "success upload story")
                    data.emit(GenericResponse(
                        success = true,
                        data = response.data
                    ))
                }
                is ApiResult.Error -> {
                    MyCustomLogger.logInfo(tag = TAG, message = "error upload story ${response.messageEn}")
                    data.emit(GenericResponse(
                        success = false,
                        message_bn = response.messageBn,
                        message_en = response.messageEn
                    ))
                }
            }
        }
    }

}