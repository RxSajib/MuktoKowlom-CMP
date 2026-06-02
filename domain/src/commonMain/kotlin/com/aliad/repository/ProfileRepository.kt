package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.MyEarnHistory
import com.aliad.model.PrivacyPolicy
import com.aliad.model.Subscription
import com.aliad.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getPremiumPlanList() : ApiResult<List<Subscription>>

    suspend fun getPrivacyPolicy() : Result<PrivacyPolicy>

    suspend fun getEarningHistory(userID : String) : ApiResult<List<MyEarnHistory>>

    suspend fun updateProfile(profileImage : ByteArray?, userID : String, name : String, emailAddress : String, phoneNumber : String,
                              phoneNumberTwo : String, address : String, bio : String) : ApiResult<User>
}