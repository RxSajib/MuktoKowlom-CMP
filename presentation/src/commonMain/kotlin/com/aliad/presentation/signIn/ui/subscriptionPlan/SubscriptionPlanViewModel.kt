package com.aliad.presentation.signIn.ui.subscriptionPlan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.ApiResult
import com.aliad.model.Subscription
import com.aliad.presentation.utils.UiState
import com.aliad.usecase.PremiumPlanUseCase
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriptionPlanViewModel constructor(
    val premiumPlanUseCase: PremiumPlanUseCase,
    val getStringData: GetStringData
) : ViewModel() {

    private var premiumPlanMutableStateFlow = MutableStateFlow< UiState<List<Subscription>>>(UiState.Loading)
    val premiumPlanStateFlow = premiumPlanMutableStateFlow.asStateFlow()

    var selectedSubscriptionIndex by mutableStateOf(2)
    var selectedPackage by mutableStateOf(Subscription())
    var loading by mutableStateOf(false)


    var sharedFlow = MutableSharedFlow<String>()

    fun sendFlow(data: String) {
        viewModelScope.launch {
            sharedFlow.emit(data)
        }
    }


    val selectedLan = flow {
        emit(getStringData.getStringData(key = AppConstant.SELECT_LOCAL).first())
    }

    init {
        getPremiumPlanList()
    }

    fun getPremiumPlanList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                premiumPlanMutableStateFlow.emit(UiState.Loading)
                loading = true
                val response = premiumPlanUseCase.getPremiumPlanList()
                loading = false
                when(response){
                    is ApiResult.Success -> {
                        premiumPlanMutableStateFlow.emit(UiState.Success(data = response.data))
                        response.data[0].also { selectedPackage = it }
                    }
                    is ApiResult.Error -> {
                        premiumPlanMutableStateFlow.emit(UiState.Error(messageBn = response.messageBn?: "Something went wrong", messageEn = response.messageEn?: "Something went wrong"))
                    }
                }
            }

        }

    }


}