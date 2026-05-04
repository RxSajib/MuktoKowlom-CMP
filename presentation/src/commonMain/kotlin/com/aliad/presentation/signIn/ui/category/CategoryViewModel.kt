package com.aliad.presentation.signIn.ui.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.MyCategory
import com.aliad.presentation.utils.MyCustomLogger
import com.aliad.presentation.utils.UiState
import com.aliad.usecase.CategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "CategoryViewModel"
class CategoryViewModel constructor(val categoryUseCase: CategoryUseCase) : ViewModel() {


    private val categoryStateFlow = MutableStateFlow<UiState<List<MyCategory>>>(UiState.Loading)
    val categoryData = categoryStateFlow.asStateFlow()
    var isLoading by mutableStateOf(false)

    init {
        fetchCategory()
    }

    fun fetchCategory() {
        viewModelScope.launch {
            categoryStateFlow.emit(UiState.Loading)
            isLoading = true
            val response = categoryUseCase.getCategory()
            isLoading = false
            when (response) {
                is ApiResult.Success -> {
                    categoryStateFlow.emit(UiState.Success(response.data))
                }

                is ApiResult.Error -> {
                    categoryStateFlow.emit(
                        UiState.Error(
                            messageBn = response.messageBn ?: "Something went wrong",
                            messageEn = response.messageEn ?: "Something went wrong"
                        )
                    )
                }
            }

        }

    }
}