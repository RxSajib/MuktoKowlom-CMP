package com.aliad.usecase

import com.aliad.model.PrivacyPolicy
import com.aliad.repository.ProfileRepository

class PrivacyPolicyUseCase constructor(val profileRepository: ProfileRepository) {

    suspend fun getPrivacyPolicy() : Result<PrivacyPolicy>{
        return profileRepository.getPrivacyPolicy()
    }
}