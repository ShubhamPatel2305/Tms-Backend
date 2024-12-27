package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.dao.AuthDao
import com.TmsBackend.Tms.Backend.models.dao.Employ
import com.TmsBackend.Tms.Backend.models.dao.UserAuth
import com.TmsBackend.Tms.Backend.models.dto.CreateUserRequest
import com.TmsBackend.Tms.Backend.models.dto.LoginRequest
import com.TmsBackend.Tms.Backend.models.dto.RefreshTokenRequest
import com.TmsBackend.Tms.Backend.models.dto.TokenResponse
import com.TmsBackend.Tms.Backend.utils.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authDao: AuthDao,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {
    fun createUser(request: CreateUserRequest): String {
        val password = generateRandomPassword()
        val employ = Employ(
            name = request.name,
            email = request.email,
            contact_number = request.contactNumber,
            role = "user",
            created_at = System.currentTimeMillis() / 1000
        )

        val id = authDao.createEmploy(employ)
        val userAuth = UserAuth(
            id = id,
            email = request.email,
            name = request.name,
            password_hash = passwordEncoder.encode(password)
        )

        authDao.createUserAuth(userAuth)
        println("Generated password for ${request.email}: $password")
        return password
    }

    fun login(request: LoginRequest): TokenResponse {
        val userPair = authDao.findUserByEmail(request.email)
            ?: throw IllegalArgumentException("User not found")

        if (!passwordEncoder.matches(request.password, userPair.second.password_hash)) {
            throw IllegalArgumentException("Invalid password")
        }

        val (employ, _) = userPair
        val accessToken = jwtUtil.generateAccessToken(employ)
        val refreshToken = jwtUtil.generateRefreshToken(employ)

        authDao.updateRefreshToken(employ.email, refreshToken)

        return TokenResponse(accessToken, refreshToken)
    }

    fun refreshToken(request: RefreshTokenRequest): TokenResponse {
        val claims = jwtUtil.validateAndGetClaims(request.refreshToken)
        val email = claims.subject

        val userPair = authDao.findUserByEmail(email)
            ?: throw IllegalArgumentException("User not found")

        val (employ, userAuth) = userPair

        if (userAuth.refresh_token != request.refreshToken) {
            throw IllegalArgumentException("Invalid refresh token")
        }

        val newAccessToken = jwtUtil.generateAccessToken(employ)
        val newRefreshToken = jwtUtil.generateRefreshToken(employ)

        authDao.updateRefreshToken(email, newRefreshToken)

        return TokenResponse(newAccessToken, newRefreshToken)
    }

    private fun generateRandomPassword(): String {
        return (1..6).map { (0..9).random() }.joinToString("")
    }
}