package com.aliad.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.MyBookItem
import com.aliad.model.Category
import com.aliad.model.GenericResponse
import com.aliad.model.mapper.DataMapper
import com.aliad.model.mapper.DataMapper.toBookModel
import com.aliad.pager.CategoryWiseBookPagingSource
import com.aliad.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    override  fun getCategoryWiseBook(
        categoryID: String,
        searchBy: String
    ): Flow<PagingData<MyBookItem>> {
      return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CategoryWiseBookPagingSource(remoteDataSources = dataSources, categoryID = categoryID, searchBy = searchBy)
            }
        ).flow.map {pagingData ->
          pagingData.map { bookItem ->
              toBookModel(bookItem = bookItem)
            }
        }
    }


}