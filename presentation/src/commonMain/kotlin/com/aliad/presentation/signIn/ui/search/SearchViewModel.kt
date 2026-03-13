package com.aliad.presentation.signIn.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.PopularSearch
import com.aliad.usecase.PopularSearchUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel constructor(val popularSearchUseCase: PopularSearchUseCase) : ViewModel() {

    var searchStoryData by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    private val popularSearchStoryMutableStateFlow = MutableStateFlow(PopularSearch())
    val popularSearch = popularSearchStoryMutableStateFlow.asStateFlow()

    init {
        fetchPopularSearch()
    }

    fun fetchPopularSearch() {
        viewModelScope.launch {
            isLoading = true
            val result = popularSearchUseCase.getPopularSearchStory()
            isLoading = false

            if(result.isSuccess){
                popularSearchStoryMutableStateFlow.emit(result.getOrNull()?: PopularSearch())

                println("success popular search story fetch data")
            }else {
                popularSearchStoryMutableStateFlow.emit( PopularSearch())

                println("error popular search story fetch data")
            }
        }
    }
}