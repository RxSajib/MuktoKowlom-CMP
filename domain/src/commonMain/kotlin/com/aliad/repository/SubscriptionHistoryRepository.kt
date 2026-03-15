package com.aliad.repository

import androidx.paging.PagingData
import com.aliad.model.Payment
import kotlinx.coroutines.flow.Flow

interface SubscriptionHistoryRepository {

    fun getSubscriptionHistory() : Flow<PagingData<Payment>>
}
