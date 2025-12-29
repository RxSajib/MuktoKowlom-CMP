package com.aliad.presentation.signIn.ui.categoryWiseBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aliad.usecase.CategoryWiseBookUseCase
import kotlinx.coroutines.launch

class CategoryWiseBookViewModel constructor(val categoryWiseBookUseCase: CategoryWiseBookUseCase) :
    ViewModel() {

    val data  = categoryWiseBookUseCase.getCategoryWiseBook(categoryID = "29", searchBy = "All")
            .cachedIn(viewModelScope)


}