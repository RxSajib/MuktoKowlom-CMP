package com.aliad.usecase

import com.aliad.model.Subscription
import com.aliad.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class PremiumPlanUseCase constructor(val profileRepository: ProfileRepository) {

    suspend fun getPremiumPlanList() : Result<List<Subscription>>{
        return profileRepository.getPremiumPlanList()
    }
}