package com.aliad.repositoryImpl

import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.Category
import com.aliad.model.GenericResponse
import com.aliad.model.mapper.DataMapper
import com.aliad.repository.CategoryRepository

class CategoryRepositoryImpl constructor(val dataSources: RemoteDataSources) : CategoryRepository {
    override suspend fun getCategory(): Result<GenericResponse<List<Category>>> {
        val dataSources = dataSources.getCategory()
        if(dataSources.isSuccess){
            val data = dataSources.getOrNull()?.data
            if (data != null) {
              val listOfCategory =  DataMapper.toCategoryList(data)
                return Result.success(GenericResponse(data = listOfCategory))
            }else {
                return Result.failure(Exception("data is null"))
            }
        }else  {
            return Result.failure(dataSources.exceptionOrNull()!!)
        }
    }
}