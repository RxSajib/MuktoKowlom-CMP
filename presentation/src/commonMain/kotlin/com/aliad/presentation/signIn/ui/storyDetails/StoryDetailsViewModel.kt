package com.aliad.presentation.signIn.ui.storyDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class StoryDetailsViewModel constructor() : ViewModel() {

    var isExpandedText by mutableStateOf(false)

    var isOpenRatingBottomSheet by mutableStateOf(false)

    var inputComment by mutableStateOf("")
    var inputRatingCount by mutableStateOf(0f)

    val isEnableRatingButton get() = inputRatingCount > 0
}