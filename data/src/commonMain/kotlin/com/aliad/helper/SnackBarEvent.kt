package com.aliad.helper

import com.aliad.model.SnackBarDetails
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object SnackBarEvent {
    private val _state = MutableStateFlow<SnackBarDetails>(SnackBarDetails())
    val state = _state.asStateFlow()

    fun save(details: SnackBarDetails) {
        _state.value = details
    }
}

object ResetAppEvent{
    private val _stateReset = MutableStateFlow<Boolean>(false)
    val reset = MutableStateFlow<Boolean>(false)

    fun triggerResetEvent(reset : Boolean){
        _stateReset.value = reset
    }

}