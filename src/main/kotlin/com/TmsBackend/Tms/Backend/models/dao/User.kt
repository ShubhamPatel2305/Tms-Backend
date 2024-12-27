package com.TmsBackend.Tms.Backend.models.dao

data class Employ(
    val id: Int? = null,
    val name: String,
    val email: String,
    val contact_number: String,
    val role: String,
    val created_at: Long
)

data class UserAuth(
    val id: Int,
    val email: String,
    val name: String,
    val password_hash: String,
    val refresh_token: String? = null
)