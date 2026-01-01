package com.aliad.repositoryImpl

import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.DashBord
import com.aliad.model.DashboardDto
import com.aliad.model.mapper.DataMapper.toDashBoardModel
import com.aliad.repository.DashBordRepository

class DashBoardRepositoryImpl constructor(private val remoteDataSources: RemoteDataSources) : DashBordRepository {
    override suspend fun getDashBoard(): Result<DashBord> {
        val response = remoteDataSources.getDashBoard()
        if(response.isSuccess){
            val data = toDashBoardModel(response.getOrNull()?: DashboardDto())
            return Result.success(data)
        }else {
            return Result.failure(response.exceptionOrNull()?: Exception("Something went wrong"))
        }
    }
}