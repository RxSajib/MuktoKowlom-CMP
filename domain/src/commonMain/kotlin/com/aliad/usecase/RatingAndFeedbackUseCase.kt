package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.Comment
import com.aliad.model.MyCommentData
import com.aliad.repository.StoryManagementRepo

class RatingAndFeedbackUseCase constructor(val storyManagementRepo: StoryManagementRepo) {

    suspend fun sendRatingAndFeedback(comment: MyCommentData) : ApiResult<Comment>{
        return storyManagementRepo.sendRatingAndFeedback(commentData = comment)
    }
}