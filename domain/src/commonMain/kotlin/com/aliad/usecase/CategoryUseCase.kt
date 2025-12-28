package com.aliad.usecase

import com.aliad.repository.CategoryRepository

class CategoryUseCase constructor(val categoryRepository: CategoryRepository) {

    suspend fun getCategory()  = categoryRepository.getCategory()
}