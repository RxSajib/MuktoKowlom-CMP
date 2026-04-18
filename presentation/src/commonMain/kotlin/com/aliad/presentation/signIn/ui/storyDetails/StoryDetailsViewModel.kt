package com.aliad.presentation.signIn.ui.storyDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.MyBookItem
import com.aliad.usecase.StoryDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoryDetailsViewModel constructor(
    val storyDetailsUseCase: StoryDetailsUseCase
) : ViewModel() {

    var isExpandedText by mutableStateOf(false)

    var isOpenRatingBottomSheet by mutableStateOf(false)

    var inputComment by mutableStateOf("")
    var inputRatingCount by mutableStateOf(0f)

    val isEnableRatingButton get() = inputRatingCount > 0

    var storyDataMutableStateFlow = MutableStateFlow(MyBookItem())
    val storyData = storyDataMutableStateFlow.asStateFlow()

    fun setStoryData(myBookItem: MyBookItem){
        viewModelScope.launch {
            storyDataMutableStateFlow.emit(myBookItem)
        }
    }

    var isLoading by mutableStateOf(false)


    fun getStoryDetails(storyID : String){
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {

           when(
               val response =
               storyDetailsUseCase.getStoryDetails(storyID = storyID)

           ){
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
}