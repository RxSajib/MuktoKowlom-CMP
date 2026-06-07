package com.aliad.usecase

import com.aliad.ApiResult
import com.aliad.model.UploadStory
import com.aliad.repository.StoryManagementRepo

class UploadStoryUseCase constructor(val storyManagementRepo: StoryManagementRepo){

    suspend fun uploadStory(titleBn : String, categoryID : String, tagsBn : List<String>, publishedDate : String,
                    summaryBn : String, imageFile : ByteArray, storyFileBn : ByteArray?, isPayable : String,
                    storyBn : String) : ApiResult<UploadStory> {
        return storyManagementRepo.uploadStory(
            titleBn = titleBn,
            categoryID = categoryID,
            tagsBn = tagsBn,
            publishedDate = publishedDate,
            summaryBn = summaryBn,
            imageFile = imageFile,
            storyFileBn = storyFileBn,
            isPayable = isPayable,
            storyBn = storyBn
        )
    }
}