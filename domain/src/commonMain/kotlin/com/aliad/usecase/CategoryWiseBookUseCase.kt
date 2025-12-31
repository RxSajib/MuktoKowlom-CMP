package com.aliad.usecase

import com.aliad.repository.CategoryRepository

class CategoryWiseBookUseCase constructor(val categoryRepository: CategoryRepository) {

     fun getCategoryWiseBook(categoryID: Int, searchBy: String) =
        categoryRepository.getCategoryWiseBook(categoryID = categoryID, searchBy = searchBy)
}