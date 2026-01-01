package com.aliad.usecase

import com.aliad.model.DashBord
import com.aliad.repository.DashBordRepository

class DashBoardUseCase constructor(val dashBordRepository: DashBordRepository) {
    suspend fun getDashBoardData() : Result<DashBord> {
        return dashBordRepository.getDashBoard()
    }
}