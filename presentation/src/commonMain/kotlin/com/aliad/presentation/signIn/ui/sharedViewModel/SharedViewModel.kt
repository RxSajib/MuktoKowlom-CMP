package com.aliad.presentation.signIn.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel constructor() : ViewModel() {


    var selectedBookID by mutableStateOf(0)


    private var isPendingStoryMutableStateFlow = MutableStateFlow(false)
    val pendingStoryState = isPendingStoryMutableStateFlow.asStateFlow()

    fun setIsPendingStory(value : Boolean){
        viewModelScope.launch {
            isPendingStoryMutableStateFlow.emit(value)
        }
    }



    private var selectedStoryTypeMutableStateFlow = MutableStateFlow("")
    val storyTypeSelected = selectedStoryTypeMutableStateFlow.asStateFlow()

    fun selectedStoryType(storyType : String){
        viewModelScope.launch {
            selectedStoryTypeMutableStateFlow.emit(storyType)
        }
    }


    private var selectedCategoryData = MutableStateFlow<com.aliad.model.MyCategory>(com.aliad.model.MyCategory())
    val selectedCategory = selectedCategoryData.asStateFlow()

    fun setCategory(category: com.aliad.model.MyCategory){
        viewModelScope.launch {
            selectedCategoryData.emit(category)
        }
    }

    var searchKeyword by mutableStateOf("")
}