package com.aliad.presentation.signIn.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.DashBord
import com.aliad.presentation.utils.UiState
import com.aliad.usecase.DashBoardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashBoardViewModel constructor(val dashBoardUseCase: DashBoardUseCase) : ViewModel() {

    private val dashBoardMutableStateFlow = MutableStateFlow<UiState<DashBord>>(UiState.Loading)
    val dashBoard = dashBoardMutableStateFlow.asStateFlow()

    var isLoading by mutableStateOf(false)

    init {
        getDashBoardData()
    }

    fun getDashBoardData(){
        viewModelScope.launch {
            dashBoardMutableStateFlow.emit(UiState.Loading)
            isLoading = true
          val result =  dashBoardUseCase.getDashBoardData()
            isLoading = false
            when(result){
                is ApiResult.Success -> {
                    dashBoardMutableStateFlow.emit(UiState.Success(data = result.data))
                }
                is ApiResult.Error -> {
                    dashBoardMutableStateFlow.emit(UiState.Error(messageEn = result.messageEn?: "Something went wrong", messageBn = result.messageBn?: "Something went wrong"))
                }
            }
        }
    }
}