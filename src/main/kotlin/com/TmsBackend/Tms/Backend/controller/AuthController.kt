package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.exceptions.ExpiredTokenException
import com.TmsBackend.Tms.Backend.exceptions.InvalidTokenException
import com.TmsBackend.Tms.Backend.models.dto.CreateUserRequest
import com.TmsBackend.Tms.Backend.models.dto.LoginRequest
import com.TmsBackend.Tms.Backend.models.dto.RefreshTokenRequest
import com.TmsBackend.Tms.Backend.models.dto.TokenResponse
import com.TmsBackend.Tms.Backend.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class AuthController(private val authService: AuthService) {

    @PostMapping("/admin/create")
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<String> {
        val password = authService.createUser(request)
        return ResponseEntity.ok("User created successfully")
    }

    @PostMapping("/auth/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(authService.login(request))
    }

    @PostMapping("/auth/refresh")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): ResponseEntity<Any> {
        return try {
            val response = authService.refreshToken(request)
            ResponseEntity.ok(response) // Case 3: Success
        } catch (e: ExpiredTokenException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired. Please log in again.") // Case 1
        } catch (e: InvalidTokenException) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refresh token.") // Case 2
        }
    }
}