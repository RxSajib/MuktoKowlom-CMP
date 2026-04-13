package com.aliad.presentation.signIn.ui.categoryWiseBook

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aliad.model.MyBookItem
import com.aliad.model.PagingUiState
import com.aliad.usecase.CategoryWiseBookUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class CategoryWiseBookViewModel constructor(
    val categoryWiseBookUseCase: CategoryWiseBookUseCase,
    val savedStateHandle: SavedStateHandle
) :
    ViewModel() {


    var searchStoryData by mutableStateOf("")


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


    //todo category by book

    companion object {
        private const val QUERY_KEY_CATEGORY_BOOK = "query_key_category_book"
        private const val QUERY_KEY_CATEGORY_ID = "query_key_category_id"
    }


    private val _queryCategorySearchName = MutableStateFlow(
        savedStateHandle.get<String>(QUERY_KEY_CATEGORY_BOOK) ?: "All"
    )
    private val _queryCategoryID = MutableStateFlow(
        savedStateHandle.get<Int>(QUERY_KEY_CATEGORY_ID) ?: 0
    )

    // Cache the latest PagingData result in a StateFlow
    private val _currentPagingData = MutableStateFlow<Flow<PagingData<MyBookItem>>?>(null)

    // Expose the paging data as a non-null flow
    @OptIn(ExperimentalCoroutinesApi::class)
    val categoryWiseSearch: Flow<PagingData<MyBookItem>> = _currentPagingData
        .flatMapLatest {
            it ?: categoryWiseBookUseCase.getCategoryWiseBook(
                categoryID = _queryCategoryID.value,
                searchBy = _queryCategorySearchName.value
            ).cachedIn(viewModelScope).also { newPagingData ->
                _currentPagingData.value = newPagingData // Cache the first-time result
            }
        }

    // Update search query function
    fun categoryByBook(search: String, categoryID: Int) {
        if (_queryCategorySearchName.value != search || _queryCategoryID.value != categoryID) {
            _queryCategorySearchName.value = search
            _queryCategoryID.value = categoryID
            savedStateHandle[QUERY_KEY_CATEGORY_BOOK] = search  // Save query in SavedStateHandle
            savedStateHandle[QUERY_KEY_CATEGORY_ID] = categoryID  // Save query in SavedStateHandle
            _currentPagingData.value = null // Invalidate cache to load new data for new search
        }
    }

    //todo category by book
}