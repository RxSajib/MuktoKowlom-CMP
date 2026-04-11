package com.aliad.usecase

import com.aliad.repository.StoryType

class SearchBookUseCase constructor(val storyType: StoryType) {

    fun searchStory(searchKey : String) = storyType.getSearchStory(searchKey = searchKey)
}