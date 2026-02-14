package com.aliad.model.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val account_holder_name: String?= null,
    val account_number: String?= null,
    val address: String? =null,
    val attach_file: String?= null,
    val bank_name: String?= null,
    val beneficiary_phone_no: String?= null,
    val bio: String?= null,
    val bkash_no: String?= null,
    val branch_name: String?= null,
    val created_at: String?= null,
    val deleted_at: String?= null,
    val district_name: String?= null,
    val dob: String?= null,
    val email: String?= null,
    val email_verified_at: String?= null,
    val facebook: String?= null,
    val id: Int?= null,
    val image: String?= null,
    val instagram: String?= null,
    val is_banned: String?= null,
    val is_verified: String?= null,
    val is_writer: String?= null,
    val is_writer_status: String?= null,
    val linkedin: String?= null,
    val nagad_no: String?= null,
    val name: String?= null,
    val phone: String?= null,
    val phone_two: String?= null,
    val provider_id: String?= null,
    val provider_name: String?= null,
    val reffer_link: String?= null,
    val rocket_no: String?= null,
    val role_id: String?= null,
    val twitter: String?= null,
    val updated_at: String?= null,
    val verification_code: String?= null
)