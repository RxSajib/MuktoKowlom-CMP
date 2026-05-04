package com.aliad.presentation.utils

import androidx.compose.runtime.Updater

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data : T) : UiState<T>()
    data class Error(val messageBn : String, val messageEn : String) : UiState<Nothing>()
}