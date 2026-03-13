package com.aliad.usecase

import com.aliad.model.PopularSearch
import com.aliad.repository.SearchRepository

class PopularSearchUseCase constructor(val searchRepository: SearchRepository) {

   suspend fun getPopularSearchStory() : Result<PopularSearch> = searchRepository.getPopularSearch()

}