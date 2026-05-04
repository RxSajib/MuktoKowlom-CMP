package com.aliad.repository
import com.aliad.ApiResult
import com.aliad.model.DashBord

interface DashBordRepository {

   suspend fun getDashBoard() : ApiResult<DashBord>
}