package com.aliad.presentation.signIn.ui.searchStoryResult

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
import com.aliad.presentation.utils.StoryType
import com.aliad.usecase.SearchBookUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class SearchStoryResultViewModel constructor(val searchBookUseCase: SearchBookUseCase,
    val savedStateHandle: SavedStateHandle) : ViewModel() {

    var searchStoryData by mutableStateOf("")
    var readFirstTimeSearchValue by mutableStateOf(false)


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
        private const val QUERY_SEARCH_KEY = "query_search_key"
    }


    private val _queryCategorySearchName = MutableStateFlow(
        savedStateHandle.get<String>(QUERY_SEARCH_KEY) ?: "All"
    )

    // Cache the latest PagingData result in a StateFlow
    private val _currentPagingData = MutableStateFlow<Flow<PagingData<MyBookItem>>?>(null)

    // Expose the paging data as a non-null flow
    @OptIn(ExperimentalCoroutinesApi::class)
    val searchStory : Flow<PagingData<MyBookItem>> = _currentPagingData
        .flatMapLatest {
            it ?: searchBookUseCase.searchStory(
                searchKey = _queryCategorySearchName.value,
            ).cachedIn(viewModelScope).also { newPagingData ->
                _currentPagingData.value = newPagingData // Cache the first-time result
            }
        }

    // Update search query function
    fun searchStory(search: String) {
        if (_queryCategorySearchName.value != search) {
            _queryCategorySearchName.value = search
            savedStateHandle[QUERY_SEARCH_KEY] = search  // Save query in SavedStateHandle
            _currentPagingData.value = null // Invalidate cache to load new data for new search
        }
    }

    //todo category by book

}