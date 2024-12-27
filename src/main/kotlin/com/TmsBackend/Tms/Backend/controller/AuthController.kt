package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.CreateUserRequest
import com.TmsBackend.Tms.Backend.models.dto.LoginRequest
import com.TmsBackend.Tms.Backend.models.dto.RefreshTokenRequest
import com.TmsBackend.Tms.Backend.models.dto.TokenResponse
import com.TmsBackend.Tms.Backend.service.AuthService
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
    fun refreshToken(@RequestBody request: RefreshTokenRequest): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(authService.refreshToken(request))
    }
}