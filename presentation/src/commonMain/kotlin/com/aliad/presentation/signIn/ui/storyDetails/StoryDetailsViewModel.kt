package com.aliad.presentation.signIn.ui.storyDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class StoryDetailsViewModel constructor() : ViewModel() {

    var isExpandedText by mutableStateOf(false)
}