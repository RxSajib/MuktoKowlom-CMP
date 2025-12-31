package com.aliad.model.mapper

import com.aliad.model.BookItem
import com.aliad.model.MyBookItem
import com.aliad.model.Category
import com.aliad.model.CategoryDto
import com.aliad.model.CategoryWiseBookDto

object DataMapper {

    fun toCategory(categoryDto: CategoryDto): Category {
        return Category(
            name = categoryDto.name,
            name_bn = categoryDto.name_bn,
            id = categoryDto.id,
            image = categoryDto.image
        )
    }

    fun toCategoryList(categoryDtoList: List<CategoryDto>): List<Category> {
        val categoryList = mutableListOf<Category>()
        categoryDtoList.forEach {
            categoryList.add(toCategory(it))
        }
        return categoryList
    }


    fun toBookModel(bookItem: BookItem): MyBookItem {
        return MyBookItem(
            category_name = bookItem.category_name,
            category_name_bn = bookItem.category_name_bn,
            created_at = bookItem.created_at,
            titleBn = bookItem.title_bn,
            image = bookItem.image,
            rating = bookItem.rating,
            authorName = bookItem.user_name
        )
    }
}