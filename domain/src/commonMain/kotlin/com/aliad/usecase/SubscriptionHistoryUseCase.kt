package com.aliad.usecase

import com.aliad.repository.SubscriptionHistoryRepository

class SubscriptionHistoryUseCase constructor(val subscriptionHistoryRepository: SubscriptionHistoryRepository) {

    fun getSubscriptionHistory() = subscriptionHistoryRepository.getSubscriptionHistory()

}