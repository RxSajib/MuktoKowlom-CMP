package com.aliad.usecase

import com.aliad.repository.StoryType

class AllReleaseUseCase constructor(val storyType: StoryType) {

    fun getAllReleaseStory(searchKey : String) = storyType.getAllReleaseStory(searchKey = searchKey)
}