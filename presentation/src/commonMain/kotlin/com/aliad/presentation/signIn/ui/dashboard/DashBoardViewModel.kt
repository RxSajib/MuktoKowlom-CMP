package com.aliad.presentation.signIn.ui.dashboard

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

    init {
        getDashBoardData()
    }

    fun getDashBoardData(){
        viewModelScope.launch {
          val result =  dashBoardUseCase.getDashBoardData()
            if(result.isSuccess){
                dashBoardMutableStateFlow.emit(result.getOrNull())
            }else {
                dashBoard.equals(result.exceptionOrNull())
            }
        }
    }
}