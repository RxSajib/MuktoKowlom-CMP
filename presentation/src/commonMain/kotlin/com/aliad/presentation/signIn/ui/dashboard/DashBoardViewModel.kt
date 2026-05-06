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
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DashBoardViewModel constructor(val dashBoardUseCase: DashBoardUseCase,
    val getStringData: GetStringData) : ViewModel() {

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

    val selectedLan = flow {
        emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
    }.flowOn(context = Dispatchers.IO)
}