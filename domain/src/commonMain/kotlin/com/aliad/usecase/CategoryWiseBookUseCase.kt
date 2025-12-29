package com.aliad.usecase

import com.aliad.repository.CategoryRepository

class CategoryWiseBookUseCase constructor(val categoryRepository: CategoryRepository) {

     fun getCategoryWiseBook(categoryID: String, searchBy: String) =
        categoryRepository.getCategoryWiseBook(categoryID = categoryID, searchBy = searchBy)
}