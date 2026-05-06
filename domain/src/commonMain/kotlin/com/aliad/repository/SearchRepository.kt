package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.PopularSearch
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getPopularSearch() : ApiResult<PopularSearch>
}