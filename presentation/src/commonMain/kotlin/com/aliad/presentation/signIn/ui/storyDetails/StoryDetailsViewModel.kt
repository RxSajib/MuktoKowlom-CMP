package com.aliad.presentation.signIn.ui.storyDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.Comment
import com.aliad.model.MyCommentData
import com.aliad.model.GenericResponse
import com.aliad.model.MyBookItem
import com.aliad.usecase.RatingAndFeedbackUseCase
import com.aliad.usecase.StoryDetailsUseCase
import com.aliad.usecase.dataStore.GetIntData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StoryDetailsViewModel constructor(
    val storyDetailsUseCase: StoryDetailsUseCase,
    val ratingAndFeedbackUseCase: RatingAndFeedbackUseCase,
    val getIntData: GetIntData
) : ViewModel() {

    var isExpandedText by mutableStateOf(false)

    var isOpenRatingBottomSheet by mutableStateOf(false)

    var inputComment by mutableStateOf("")
    var inputRatingCount by mutableStateOf(0f)

    val isEnableRatingButton get() = inputRatingCount > 0

    var storyDataMutableStateFlow = MutableStateFlow(MyBookItem())
    val storyData = storyDataMutableStateFlow.asStateFlow()

    fun setStoryData(myBookItem: MyBookItem) {
        viewModelScope.launch {
            storyDataMutableStateFlow.emit(myBookItem)
        }
    }

    var isLoading by mutableStateOf(false)


    fun getStoryDetails(storyID: String) {
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {

            when (
                val response =
                    storyDetailsUseCase.getStoryDetails(storyID = storyID)

            ) {
                is ApiResult.Success -> {
                    print("success fetch data ${response.data}")
                    setStoryData(myBookItem = response.data)
                    isLoading = false
                }

                is ApiResult.Error -> {
                    isLoading = false
                }
            }
        }
    }

    // rating and feedback implementation
    var loadingRatingAndFeedback by mutableStateOf(false)
    var ratingAndFeedbackData = MutableSharedFlow<GenericResponse<Comment>>()

    fun sendRatingAndFeedback(storyID : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingRatingAndFeedback = true
            val response = ratingAndFeedbackUseCase.sendRatingAndFeedback(comment = MyCommentData(
                comment = inputComment,
                rating = inputRatingCount.toInt(),
                story_id = storyID,
                user_id = getIntData.getIntData(key = AppConstant.USER_ID).first()
            ))
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
}