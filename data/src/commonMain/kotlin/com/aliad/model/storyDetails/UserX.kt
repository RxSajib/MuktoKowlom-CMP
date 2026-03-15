package com.aliad.model.storyDetails

import kotlinx.serialization.Serializable

@Serializable
data class UserX(
    val account_holder_name: String,
    val account_number: String,
    val address: String,
    val attach_file: String,
    val bank_name: String,
    val beneficiary_phone_no: String,
    val bio: String,
    val bkash_no: String,
    val branch_name: String,
    val created_at: String,
    val deleted_at: String,
    val district_name: String,
    val dob: String,
    val email: String,
    val email_verified_at: String,
    val facebook: String,
    val id: Int,
    val image: String,
    val instagram: String,
    val is_banned: String,
    val is_verified: String,
    val is_writer: String,
    val is_writer_status: String,
    val linkedin: String,
    val nagad_no: String,
    val name: String,
    val phone: String,
    val phone_two: String,
    val provider_id: String,
    val provider_name: String,
    val reffer_link: String,
    val rocket_no: String,
    val role_id: String,
    val twitter: String,
    val updated_at: String,
    val verification_code: String
)