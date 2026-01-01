package com.aliad.repository
import com.aliad.model.DashBord

interface DashBordRepository {

   suspend fun getDashBoard() : Result<DashBord>
}