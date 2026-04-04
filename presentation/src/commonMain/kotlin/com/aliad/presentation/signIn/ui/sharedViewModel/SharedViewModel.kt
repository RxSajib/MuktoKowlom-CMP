package com.aliad.presentation.signIn.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.repository.StoryType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel constructor() : ViewModel() {

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
}