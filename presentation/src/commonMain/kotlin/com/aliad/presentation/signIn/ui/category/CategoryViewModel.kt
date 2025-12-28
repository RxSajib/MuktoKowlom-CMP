package com.aliad.presentation.signIn.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.Category
import com.aliad.usecase.CategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel constructor(val categoryUseCase: CategoryUseCase) : ViewModel() {


    private val categoryStateFlow = MutableStateFlow<List<Category>>(emptyList())
    val categoryData = categoryStateFlow.asStateFlow()

    init {
        fetchCategory()
    }

    fun fetchCategory() {
        viewModelScope.launch {
         val response =   categoryUseCase.getCategory()
            if(response.isSuccess){
                println("data fetch success viewmodel ${response.getOrNull()}")
                categoryStateFlow.emit(response.getOrNull()?.data?: emptyList())
            }else {
                println("data fetch failed ${response.exceptionOrNull()}")
                categoryStateFlow.emit(emptyList())
            }
        }

    }
}