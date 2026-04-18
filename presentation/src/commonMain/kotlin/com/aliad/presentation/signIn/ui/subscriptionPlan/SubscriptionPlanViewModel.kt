package com.aliad.presentation.signIn.ui.subscriptionPlan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.Subscription
import com.aliad.usecase.PremiumPlanUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriptionPlanViewModel constructor(val premiumPlanUseCase: PremiumPlanUseCase) : ViewModel() {

    private var premiumPlanMutableStateFlow = MutableStateFlow<List<Subscription>>(emptyList())
    val premiumPlanStateFlow = premiumPlanMutableStateFlow.asStateFlow()

    var selectedSubscriptionIndex by mutableStateOf(1)
    var selectedPackage by mutableStateOf(Subscription())
    var loading by mutableStateOf(false)

    init {
        getPremiumPlanList()
    }

    fun getPremiumPlanList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                loading = true
                val response = premiumPlanUseCase.getPremiumPlanList()
                loading = false
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


    
}