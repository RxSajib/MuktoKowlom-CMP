package com.aliad.model

import androidx.compose.runtime.Immutable
import androidx.paging.LoadState

@Immutable
data class PagingUiState(
    val isRefreshing: Boolean = false,
    val isAppending: Boolean = false,
    val isPrepending: Boolean = false,
    val refreshError: LoadState.Error? = null,
    val appendError: LoadState.Error? = null,
    val prependError: LoadState.Error? = null,
    val isEmpty: Boolean = false
)
