package com.TmsBackend.Tms.Backend.models.dto

data class CreateUserRequest(
    val name: String,
    val email: String,
    val contactNumber: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)

data class RefreshTokenRequest(
    val refreshToken: String
)