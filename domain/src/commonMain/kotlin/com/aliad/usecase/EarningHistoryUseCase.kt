package com.aliad.usecase

import com.aliad.repository.ProfileRepository

class EarningHistoryUseCase constructor(val profileRepository: ProfileRepository) {

    suspend fun getEarningHistory(userID : String) = profileRepository.getEarningHistory(userID = userID)
}