package com.aliad.presentation.signIn.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.PopularSearch
import com.aliad.presentation.utils.UiState
import com.aliad.usecase.PopularSearchUseCase
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel constructor(val popularSearchUseCase: PopularSearchUseCase, val getStringData: GetStringData) : ViewModel() {

    var searchStoryData by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    private val popularSearchStoryMutableStateFlow = MutableStateFlow<UiState<PopularSearch>>(
        UiState.Loading
    )
    val popularSearch = popularSearchStoryMutableStateFlow.asStateFlow()

    init {
        fetchPopularSearch()
    }

    fun fetchPopularSearch() {
        viewModelScope.launch {
            popularSearchStoryMutableStateFlow.emit(UiState.Loading)
            isLoading = true
            val result = popularSearchUseCase.getPopularSearchStory()
            isLoading = false


            when (result) {
                is ApiResult.Success -> {
                    popularSearchStoryMutableStateFlow.emit(UiState.Success(data = result.data))
                }

                is ApiResult.Error -> {
                    popularSearchStoryMutableStateFlow.emit(
                        UiState.Error(
                            messageBn = result.messageBn ?: "Something went wrong",
                            messageEn = result.messageEn ?: "Something went wrong"
                        )
                    )
                }
            }
        }
    }

    val selectedLan = flow {
        emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
    }.flowOn(context = Dispatchers.IO)
}