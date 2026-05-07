package com.aliad.usecase

import com.aliad.repository.StoryType

class PublishedPendingStoryUseCase constructor(val storyType: StoryType) {

    fun getPublishedStory(userID : String) = storyType.getAllPendingStoryList(userID = userID)
}