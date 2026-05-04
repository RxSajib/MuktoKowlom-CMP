package com.aliad.repository

import androidx.paging.PagingData
import com.aliad.ApiResult
import com.aliad.model.MyBookItem
import com.aliad.model.MyCategory
import com.aliad.model.GenericResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategory() : ApiResult<List<MyCategory>>

    fun getCategoryWiseBook(categoryID : Int, searchBy : String) : Flow<PagingData<MyBookItem>>
}