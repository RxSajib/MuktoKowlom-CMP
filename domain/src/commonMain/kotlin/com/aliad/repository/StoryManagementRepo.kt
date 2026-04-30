package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.Comment
import com.aliad.model.MyCommentData

interface StoryManagementRepo {

    suspend fun sendRatingAndFeedback(commentData: MyCommentData) : ApiResult<Comment>
}