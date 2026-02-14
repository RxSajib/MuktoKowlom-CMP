package com.aliad.repository

import com.aliad.model.PrivacyPolicy
import com.aliad.model.Subscription
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getPremiumPlanList() : Result<List<Subscription>>

    suspend fun getPrivacyPolicy() : Result<PrivacyPolicy>
}