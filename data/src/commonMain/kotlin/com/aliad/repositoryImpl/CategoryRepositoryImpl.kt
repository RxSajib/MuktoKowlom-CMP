package com.aliad.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aliad.ApiResult
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.MyBookItem
import com.aliad.model.MyCategory
import com.aliad.model.GenericResponse
import com.aliad.model.mapper.DataMapper
import com.aliad.model.mapper.DataMapper.toBookModel
import com.aliad.pager.CategoryWiseBookPagingSource
import com.aliad.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl constructor(val dataSources: RemoteDataSources) : CategoryRepository {
    override suspend fun getCategory(): ApiResult<List<MyCategory>> {
        when(val dataSources = dataSources.getCategory()){
            is ApiResult.Success -> {
                val categoryData = dataSources.data.data
                return ApiResult.Success(data = DataMapper.toMyCategoryList(list = categoryData?: emptyList()))
            }
            is ApiResult.Error -> {
                return ApiResult.Error(
                    messageEn = dataSources.messageEn?: "Something went wrong",
                    messageBn = dataSources.messageBn?: "Something went wrong"
                )
            }
        }
    }

    override  fun getCategoryWiseBook(
        categoryID: Int,
        searchBy: String
    ): Flow<PagingData<MyBookItem>> {
      return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CategoryWiseBookPagingSource(remoteDataSources = dataSources, categoryID = categoryID, searchKey = searchBy)
            }
        ).flow.map {pagingData ->
          pagingData.map { bookItem ->
              toBookModel(bookItem = bookItem)
            }
        }
    }


}