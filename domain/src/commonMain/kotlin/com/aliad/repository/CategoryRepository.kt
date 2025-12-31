package com.aliad.repository

import androidx.paging.PagingData
import com.aliad.model.MyBookItem
import com.aliad.model.Category
import com.aliad.model.GenericResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategory() : Result<GenericResponse<List<Category>>>

    fun getCategoryWiseBook(categoryID : Int, searchBy : String) : Flow<PagingData<MyBookItem>>
}