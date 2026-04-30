package com.aliad.repositoryImpl

import com.aliad.ApiResult
import com.aliad.dataSource.RemoteDataSources
import com.aliad.model.Comment
import com.aliad.model.MyCommentData
import com.aliad.model.CommentDto
import com.aliad.model.mapper.DataMapper
import com.aliad.repository.StoryManagementRepo

class StoryManagementImpl constructor(val remoteDataSources: RemoteDataSources) : StoryManagementRepo {
    override suspend fun sendRatingAndFeedback(commentData: MyCommentData): ApiResult<Comment> {
        when(val response = remoteDataSources.sendFeedback(commentData = commentData)){
            is ApiResult.Success -> {
                val commentDto = response.data.data
                return ApiResult.Success(DataMapper.commentDtoToComment(commentDto = commentDto?: CommentDto()))
            }
            is ApiResult.Error -> {
                return ApiResult.Error(messageBn = response.messageBn, messageEn = response.messageEn)
            }
        }
    }
}