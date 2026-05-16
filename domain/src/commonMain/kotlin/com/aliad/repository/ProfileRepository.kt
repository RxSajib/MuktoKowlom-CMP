package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.MyEarnHistory
import com.aliad.model.PrivacyPolicy
import com.aliad.model.Subscription
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getPremiumPlanList() : ApiResult<List<Subscription>>

    suspend fun getPrivacyPolicy() : Result<PrivacyPolicy>

    suspend fun getEarningHistory(userID : String) : ApiResult<List<MyEarnHistory>>
}