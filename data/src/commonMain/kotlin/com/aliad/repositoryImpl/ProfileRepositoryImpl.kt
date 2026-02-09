package com.aliad.repositoryImpl

import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.Subscription
import com.aliad.model.SubscriptionDto
import com.aliad.model.mapper.DataMapper.toSubscriptionPlanList
import com.aliad.repository.ProfileRepository

class ProfileRepositoryImpl constructor(val dataSources: RemoteDataSources) : ProfileRepository {
    override suspend fun getPremiumPlanList(): Result<List<Subscription>> {
        val response = dataSources.getSubscriptionPlans()
        val list : ArrayList<SubscriptionDto> = ArrayList()
        if(response.isSuccess){
          //  list.clear()
            val data = response.getOrNull()
         //   list.add(data?.data?: SubscriptionDto())
            val x = toSubscriptionPlanList(
                subscriptionDto =  data?.data?: emptyList()
            )
            return Result.success(x)
        }else {
           return Result.failure(Exception(response.exceptionOrNull()?.message?: "Something went wrong"))
        }
    }
}