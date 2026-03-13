package com.aliad.repositoryImpl

import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.PopularSearch
import com.aliad.model.PopularSearchDto
import com.aliad.model.mapper.DataMapper.toPopularSearch
import com.aliad.repository.SearchRepository

class SearchRepositoryImpl constructor(val remoteDataSources: RemoteDataSources): SearchRepository {
    override suspend fun getPopularSearch(): Result<PopularSearch> {
        val response = remoteDataSources.getPopularSearch()
        if(response.isSuccess){
            val data = response.getOrNull()
            return Result.success(toPopularSearch(data?: PopularSearchDto()))
        }else {
            return Result.failure(response.exceptionOrNull()?: Exception("Something went wrong"))
        }
    }
}