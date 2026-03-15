package com.aliad.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.Payment
import com.aliad.model.mapper.DataMapper.toPayment
import com.aliad.pager.subscription_history.SubscriptionHistoryPager
import com.aliad.repository.SubscriptionHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SubscriptionHistoryRepositoryImpl constructor(val remoteDataSources: RemoteDataSources) : SubscriptionHistoryRepository {
    override fun getSubscriptionHistory(): Flow<PagingData<Payment>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SubscriptionHistoryPager(remoteDataSources = remoteDataSources)}
        ).flow.map {
            it.map {myPayment ->
                toPayment(myPayment)
            }
        }
    }
}