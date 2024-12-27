package com.TmsBackend.Tms.Backend.utils

import com.TmsBackend.Tms.Backend.models.dao.Employ
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.access.expiration}") private val accessExpiration: Long,
    @Value("\${jwt.refresh.expiration}") private val refreshExpiration: Long
) {
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateAccessToken(employ: Employ): String {
        return Jwts.builder()
            .setSubject(employ.email)
            .claim("id", employ.id)
            .claim("role", employ.role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + accessExpiration))
            .signWith(key)
            .compact()
    }

    fun generateRefreshToken(employ: Employ): String {
        return Jwts.builder()
            .setSubject(employ.email)
            .claim("id", employ.id)
            .claim("role", employ.role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + refreshExpiration))
            .signWith(key)
            .compact()
    }

    fun validateAndGetClaims(token: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid token")
        }
    }
}