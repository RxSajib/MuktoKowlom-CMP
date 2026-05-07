package com.aliad.presentation.signIn.ui.publishedPendingStory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.aliad.model.PagingUiState
import com.aliad.usecase.PublishedPendingStoryUseCase
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow


class PublishedPendingStoryViewModel constructor(
    val publishedPendingStoryUseCase: PublishedPendingStoryUseCase,
    val getIntData: GetIntData,
    val getStringData: GetStringData
) : ViewModel() {



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


    val userID = flow {
        emit(getIntData.getIntData(key = AppConstant.USER_ID).first())
    }

    val getSelectedLn = flow {
        emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
    }


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val pendingStory = userID
        .flatMapLatest { storyId ->
            publishedPendingStoryUseCase.getPublishedStory(storyId.toString())
        }.cachedIn(viewModelScope)


}