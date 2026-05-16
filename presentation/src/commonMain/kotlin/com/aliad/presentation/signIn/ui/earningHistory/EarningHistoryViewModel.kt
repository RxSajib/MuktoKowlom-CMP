package com.aliad.presentation.signIn.ui.earningHistory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.MyEarnHistory
import com.aliad.presentation.utils.MyCustomLogger
import com.aliad.presentation.utils.UiState
import com.aliad.usecase.EarningHistoryUseCase
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

private const val TAG = "EarningHistoryViewModel"

class EarningHistoryViewModel constructor(
    val earningHistoryUseCase: EarningHistoryUseCase,
    val getIntData: GetIntData,
    val getStringData: GetStringData
) : ViewModel() {

    var loading by mutableStateOf(false)
    private val earningHistoryMutableStateFlow = MutableStateFlow<UiState<List<MyEarnHistory>>>(
        UiState.Loading
    )
    val earningHistoryData = earningHistoryMutableStateFlow.asStateFlow()

    val userID = flow {
        emit(getIntData.getIntData(key = AppConstant.USER_ID).first())
    }

    val selectedLan = flow {
        emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
    }

    init {
        getEarningHistory()
    }

    fun getEarningHistory() {
        viewModelScope.launch {
            loading = true
            earningHistoryMutableStateFlow.emit(UiState.Loading)
            userID.collect { userID ->
                val response = earningHistoryUseCase.getEarningHistory(userID = userID.toString())
                loading = false
                when (response) {
                    is ApiResult.Success -> {
                        earningHistoryMutableStateFlow.emit(UiState.Success(response.data))
                    }

                    is ApiResult.Error -> {
                        earningHistoryMutableStateFlow.emit(
                            UiState.Error(
                                messageEn = response.messageEn?: "Something went wrong",
                                messageBn = response.messageBn ?: "Something went wrong"
                            )
                        )
                    }
                }
            }
        }
    }

}