package com.aliad.presentation.signIn.ui.subscriptionPlan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.Subscription
import com.aliad.usecase.PremiumPlanUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SubscriptionPlanViewModel constructor(val premiumPlanUseCase: PremiumPlanUseCase) : ViewModel() {

    private var premiumPlanMutableStateFlow = MutableStateFlow<List<Subscription>>(emptyList())
    val premiumPlanStateFlow = premiumPlanMutableStateFlow.asStateFlow()

    var selectedSubscriptionIndex by mutableStateOf(1)
    var selectedPackage by mutableStateOf(Subscription())


    init {
        getPremiumPlanList()
    }

    fun getPremiumPlanList() {
        viewModelScope.launch {
           val response = premiumPlanUseCase.getPremiumPlanList()
            if(response.isSuccess){
                premiumPlanMutableStateFlow.value = response.getOrNull()?: emptyList()
                selectedPackage = response.getOrNull()?.get(0)?: Subscription()
            }
            else {
                premiumPlanMutableStateFlow.value = emptyList()
            }
        }
        
    }


    
}