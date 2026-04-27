package com.aliad.presentation.signIn.ui.uploadStories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UploadStoriesViewModel : ViewModel() {

    var showCategoryDialog by mutableStateOf(false)
    var showCalender by mutableStateOf(false)
    var selectStoryIsFree by mutableStateOf(false)

    private var storyTitleMutableStateFlow = MutableStateFlow<String>("")
    val storyTitleFlow = storyTitleMutableStateFlow.asStateFlow()

    private var categoryMutableStateFlow = MutableStateFlow<String>("")
    val categoryFlow = categoryMutableStateFlow.asStateFlow()

    private var publishedDateMutableStateFlow = MutableStateFlow<String>("")
    val publishedDateFlow = publishedDateMutableStateFlow.asStateFlow()

    private var tagsMutableStateFlow = MutableStateFlow<String>("")
    val tagsFlow = tagsMutableStateFlow.asStateFlow()

    private var storySummaryMutableStateFlow = MutableStateFlow<String>("")
    val storySummaryFlow = storySummaryMutableStateFlow.asStateFlow()

    private var fullStoryMutableStateFlow = MutableStateFlow<String>("")
    val fullStoryFlow = fullStoryMutableStateFlow.asStateFlow()


    fun inputTitle(title : String){
        viewModelScope.launch {
            storyTitleMutableStateFlow.emit(title)
        }
    }
    fun inputCategory(category : String){
        viewModelScope.launch {
            categoryMutableStateFlow.emit(category)
        }
    }
    fun inputPublishedDate(publishedDate : String){
        viewModelScope.launch {
            publishedDateMutableStateFlow.emit(publishedDate)
        }
    }
    fun inputStorySummary(storySummary : String){
        viewModelScope.launch {
            storySummaryMutableStateFlow.emit(storySummary)
        }
    }
    fun inputFullStoryDetails(fullStoryDetails : String){
        viewModelScope.launch {
            fullStoryMutableStateFlow.emit(fullStoryDetails)
        }
    }
}