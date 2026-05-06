package com.aliad.presentation.signIn.ui.mostPopularStory

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
import com.aliad.usecase.StoryTypeUseCase
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MostPopularStoryViewModel constructor(
    val storyTypeUseCase: StoryTypeUseCase,
    val savedStateHandle: SavedStateHandle,
    val getStringData: GetStringData
) : ViewModel() {

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
        private const val QUERY_SEARCH_KEY = "query_search_key"
    }


    private val _queryCategorySearchName = MutableStateFlow(
        savedStateHandle.get<String>(QUERY_SEARCH_KEY) ?: "All"
    )

    // Cache the latest PagingData result in a StateFlow
    private val _currentPagingData = MutableStateFlow<Flow<PagingData<MyBookItem>>?>(null)


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val storyData =
        _queryCategorySearchName
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                storyTypeUseCase.getStoryType(
                    searchKey = query,
                    searchType = StoryType.MOST_POPULAR_STORY.name
                )
            }
            .cachedIn(viewModelScope)

    // Update search query function
    fun searchStory(search: String) {
        _queryCategorySearchName.value = search
        savedStateHandle[QUERY_SEARCH_KEY] = search  // Save query in SavedStateHandle
        _currentPagingData.value = null // Invalidate cache to load new data for new search
    }

    //todo category by book

    val selectedLan = flow {
        emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
    }.flowOn(context = Dispatchers.IO)
}