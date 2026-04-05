package com.aliad.presentation.signIn.ui.mostPopularStory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MostPopularStoryViewModel : ViewModel() {

    var searchStoryData by mutableStateOf("")
}