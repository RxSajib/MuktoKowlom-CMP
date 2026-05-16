package com.aliad.repositoryImpl

import com.aliad.ApiResult
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.MyEarnHistory
import com.aliad.model.PrivacyPolicy
import com.aliad.model.PrivacyPolicyDto
import com.aliad.model.Subscription
import com.aliad.model.SubscriptionDto
import com.aliad.model.earn_history.EarnHistoryDto
import com.aliad.model.mapper.DataMapper.earningHistoryDtoToEarningHistory
import com.aliad.model.mapper.DataMapper.toPrivacyPolicy
import com.aliad.model.mapper.DataMapper.toSubscriptionPlanList
import com.aliad.repository.ProfileRepository
import com.aliad.utils.MyCustomLogger

private const val TAG = "ProfileRepositoryImpl"
class ProfileRepositoryImpl constructor(val dataSources: RemoteDataSources) : ProfileRepository {
    override suspend fun getPremiumPlanList(): ApiResult<List<Subscription>> {
        return when (val response = dataSources.getSubscriptionPlans()) {
            is ApiResult.Success -> {
                ApiResult.Success(
                    data = toSubscriptionPlanList(
                        subscriptionDto = response.data.data ?: emptyList()
                    )
                )
            }

            is ApiResult.Error -> {
                ApiResult.Error(messageEn = response.messageEn, messageBn = response.messageBn)
            }
        }
    }

    override suspend fun getPrivacyPolicy(): Result<PrivacyPolicy> {
        val response = dataSources.getPrivacyPolicy()
        if (response.isSuccess) {
            val data = response.getOrNull()
            val toPrivacyPolicy =
                toPrivacyPolicy(privacyPolicyDto = data?.data ?: PrivacyPolicyDto())
            return Result.success(toPrivacyPolicy)
        } else {
            return Result.failure(
                Exception(
                    response.exceptionOrNull()?.message ?: "Something went wrong"
                )
            )
        }
    }

    override suspend fun getEarningHistory(userID: String): ApiResult<List<MyEarnHistory>> {
        when(val response = dataSources.storyEarnHistory(userID = userID)){
            is ApiResult.Success -> {
                val data = earningHistoryDtoToEarningHistory(earnHistoryDto =  response.data.data?: EarnHistoryDto())
                MyCustomLogger.logInfo(tag = TAG, message = "earning history data $data")
                return ApiResult.Success(data)
            }
            is ApiResult.Error -> {
                MyCustomLogger.logInfo(tag = TAG, message = "error ${response.messageEn}")
                return ApiResult.Error(messageBn = response.messageBn, messageEn = response.messageEn)
            }
        }
    }
}