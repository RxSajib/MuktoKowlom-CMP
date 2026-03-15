package com.aliad.presentation.signIn.ui.subscriptionHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aliad.usecase.SubscriptionHistoryUseCase

class SubscriptionHistoryViewModel constructor(val subscriptionHistoryUseCase: SubscriptionHistoryUseCase) : ViewModel() {

    fun getSubscriptionHistory() = subscriptionHistoryUseCase.getSubscriptionHistory().cachedIn(viewModelScope)

}