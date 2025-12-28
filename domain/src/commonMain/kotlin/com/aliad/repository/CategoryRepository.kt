package com.aliad.repository

import com.aliad.model.Category
import com.aliad.model.GenericResponse

interface CategoryRepository {

    suspend fun getCategory() : Result<GenericResponse<List<Category>>>
}