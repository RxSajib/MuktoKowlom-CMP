package com.aliad.repository

import com.aliad.ApiResult
import com.aliad.model.Comment
import com.aliad.model.GenericResponse
import com.aliad.model.MyCommentData
import com.aliad.model.UploadStory

interface StoryManagementRepo {

    suspend fun sendRatingAndFeedback(commentData: MyCommentData) : ApiResult<Comment>

    suspend fun uploadStory(titleBn : String, categoryID : String, tagsBn : List<String>, publishedDate : String,
                            summaryBn : String, imageFile : ByteArray, storyFileBn : ByteArray?, isPayable : String,
                            storyBn : String) : ApiResult<UploadStory>
}