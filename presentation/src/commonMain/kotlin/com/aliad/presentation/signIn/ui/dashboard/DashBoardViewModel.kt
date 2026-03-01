package com.aliad.presentation.signIn.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.DashBord
import com.aliad.usecase.DashBoardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashBoardViewModel constructor(val dashBoardUseCase: DashBoardUseCase) : ViewModel() {

    private val dashBoardMutableStateFlow = MutableStateFlow<DashBord?>(null)
    val dashBoard = dashBoardMutableStateFlow.asStateFlow()

    var isLoading by mutableStateOf(false)

    init {
        getDashBoardData()
    }

    fun getDashBoardData(){
        viewModelScope.launch {
            isLoading = true
          val result =  dashBoardUseCase.getDashBoardData()
            isLoading = false
            if(result.isSuccess){
                dashBoardMutableStateFlow.emit(result.getOrNull())
            }else {
                dashBoard.equals(result.exceptionOrNull())
            }
        }
    }
}