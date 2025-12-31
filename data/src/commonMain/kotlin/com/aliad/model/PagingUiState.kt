package com.aliad.model

import androidx.paging.LoadState


data class PagingUiState(
    val isRefreshing: Boolean = false,
    val isAppending: Boolean = false,
    val isPrepending: Boolean = false,
    val refreshError: LoadState.Error? = null,
    val appendError: LoadState.Error? = null,
    val prependError: LoadState.Error? = null,
    val isEmpty: Boolean = false
)
