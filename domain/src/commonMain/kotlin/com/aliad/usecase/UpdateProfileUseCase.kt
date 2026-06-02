package com.aliad.usecase

import com.aliad.repository.ProfileRepository

class UpdateProfileUseCase constructor(val profileRepository: ProfileRepository) {

    suspend fun editProfileInfo(userID : String, name : String, emailAddress : String, phone : String, phoneTwo : String, address : String,
                        bio : String)  = profileRepository.updateProfile(
        userID = userID,
        name = name,
        emailAddress = emailAddress,
        phoneNumber = phone,
        phoneNumberTwo = phoneTwo,
        address = address,
        bio = bio
    )

}