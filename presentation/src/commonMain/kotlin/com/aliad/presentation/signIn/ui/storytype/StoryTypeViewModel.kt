package com.aliad.presentation.signIn.ui.storytype

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.aliad.model.PagingUiState
import com.aliad.usecase.StoryTypeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoryTypeViewModel constructor(val storyTypeUseCase: StoryTypeUseCase) : ViewModel() {

    val storysData = storyTypeUseCase.getStoryType(searchKey = "All", searchType = "PopularStory")
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