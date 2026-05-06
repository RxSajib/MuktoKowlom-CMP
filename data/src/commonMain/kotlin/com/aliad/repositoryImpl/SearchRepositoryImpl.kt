package com.aliad.repositoryImpl

import com.aliad.ApiResult
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.PopularSearch
import com.aliad.model.PopularSearchDto
import com.aliad.model.mapper.DataMapper.toPopularSearch
import com.aliad.repository.SearchRepository

class SearchRepositoryImpl constructor(val remoteDataSources: RemoteDataSources): SearchRepository {
    override suspend fun getPopularSearch(): ApiResult<PopularSearch> {
     return when ( val response = remoteDataSources.getPopularSearch()){
          is ApiResult.Success -> {
              ApiResult.Success(data = toPopularSearch(response.data))
          }
          is ApiResult.Error -> {
              ApiResult.Error(messageEn = response.messageEn, messageBn = response.messageBn)
          }
      }
    }
}