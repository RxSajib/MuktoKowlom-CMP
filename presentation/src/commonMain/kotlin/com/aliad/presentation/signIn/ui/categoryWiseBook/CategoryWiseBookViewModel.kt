package com.aliad.presentation.signIn.ui.categoryWiseBook

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aliad.model.MyBookItem
import com.aliad.model.PagingUiState
import com.aliad.usecase.CategoryWiseBookUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryWiseBookViewModel constructor(val categoryWiseBookUseCase: CategoryWiseBookUseCase) :
    ViewModel() {


        var searchStoryData by mutableStateOf("")

    val data =
        categoryWiseBookUseCase.getCategoryWiseBook(categoryID = 29, searchBy = "All")
            .cachedIn(viewModelScope)


    private val _pagingUiState = MutableStateFlow(PagingUiState())
    val pagingUiState: StateFlow<PagingUiState> = _pagingUiState
    fun updatePagingLoadStates(loadStates: CombinedLoadStates, itemCount: Int) {
        _pagingUiState.value = PagingUiState(
            isRefreshing = loadStates.refresh is LoadState.Loading,
            isAppending = loadStates.append is LoadState.Loading,
            isPrepending = loadStates.prepend is LoadState.Loading,
            refreshError = loadStates.refresh as? LoadState.Error,
            appendError = loadStates.append as? LoadState.Error,
            prependError = loadStates.prepend as? LoadState.Error,
            isEmpty = loadStates.refresh is LoadState.NotLoading && itemCount == 0
        )
    }
}