package com.aliad.presentation.signIn.ui.storyDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aliad.ApiResult
import com.aliad.model.Comment
import com.aliad.model.MyCommentData
import com.aliad.model.GenericResponse
import com.aliad.model.MyBookItem
import com.aliad.presentation.utils.UiState
import com.aliad.usecase.RatingAndFeedbackUseCase
import com.aliad.usecase.StoryDetailsUseCase
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.utils.MyCustomLogger
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


private const val TAG = "StoryDetailsViewModel"

class StoryDetailsViewModel constructor(
    val storyDetailsUseCase: StoryDetailsUseCase,
    val ratingAndFeedbackUseCase: RatingAndFeedbackUseCase,
    val getIntData: GetIntData,
    val savedStateHandle: SavedStateHandle,
    val storyID: String
) : ViewModel() {

    private var storyIDSaveStateHandle: String = "SaveStateHandleByStoryID"
    var isExpandedText by mutableStateOf(false)

    var isOpenRatingBottomSheet by mutableStateOf(false)

    var inputComment by mutableStateOf("")
    var inputRatingCount by mutableStateOf(0f)

    val isEnableRatingButton get() = inputRatingCount > 0

    var storyDataMutableStateFlow = MutableStateFlow<UiState<MyBookItem>>(UiState.Loading)
    val storyData = storyDataMutableStateFlow.asStateFlow()

   /* fun setStoryData(myBookItem: MyBookItem) {
        viewModelScope.launch {
            storyDataMutableStateFlow.emit(myBookItem)
        }
    }*/

    var isLoading by mutableStateOf(false)
    val searchKey = MutableStateFlow<String>(savedStateHandle[storyIDSaveStateHandle] ?: "0")

    init {
        try {
            getStoryDetails()
        } catch (e: Exception) {
            MyCustomLogger.logInfo(tag = TAG, message = e.message?: "Something went wrong")
        }
    }


    fun getStoryDetails() {

        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            storyDataMutableStateFlow.emit(UiState.Loading)
            when (
                val response =
                    storyDetailsUseCase.getStoryDetails(storyID = storyID)

            ) {
                is ApiResult.Success -> {
                    print("success fetch data ${response.data}")
                    storyDataMutableStateFlow.emit(UiState.Success(data = response.data))
                    isLoading = false
                }

                is ApiResult.Error -> {
                    storyDataMutableStateFlow.emit(UiState.Error(
                        messageBn = response.messageBn?: "Something went wrong",
                        messageEn = response.messageEn?: "Something went wrong"
                    ))
                    isLoading = false
                }
            }
        }
    }

    // rating and feedback implementation
    var loadingRatingAndFeedback by mutableStateOf(false)
    var ratingAndFeedbackData = MutableSharedFlow<GenericResponse<Comment>>()

    fun sendRatingAndFeedback(storyID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingRatingAndFeedback = true
            val response = ratingAndFeedbackUseCase.sendRatingAndFeedback(
                comment = MyCommentData(
                    comment = inputComment,
                    rating = inputRatingCount.toInt(),
                    story_id = storyID,
                    user_id = getIntData.getIntData(key = AppConstant.USER_ID).first()
                )
            )
            loadingRatingAndFeedback = false



            when (response) {
                is ApiResult.Success -> {
                    //  print("rating data success")
                    ratingAndFeedbackData.emit(GenericResponse(data = response.data, status = true))
                }

                is ApiResult.Error -> {
                    // print("error rating data")
                    ratingAndFeedbackData.emit(
                        GenericResponse(
                            status = false,
                            message_en = response.messageEn,
                            message_bn = response.messageBn
                        )
                    )
                }
            }
        }
    }


    // rating and feedback implementation


    fun searchStoryDetailsByStoryID() {
        savedStateHandle[storyIDSaveStateHandle] = storyID
    }
}