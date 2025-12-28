package com.aliad.model.mapper

import com.aliad.model.Category
import com.aliad.model.CategoryDto

object DataMapper {

    fun toCategory(categoryDto: CategoryDto) : Category{
        return Category(
            name = categoryDto.name,
            name_bn = categoryDto.name_bn,
            id = categoryDto.id,
            image = categoryDto.image
        )
    }
    fun toCategoryList(categoryDtoList: List<CategoryDto>) : List<Category>{
        val categoryList = mutableListOf<Category>()
        categoryDtoList.forEach {
            categoryList.add(toCategory(it))
        }
        return categoryList
    }
}