package com.TmsBackend.Tms.Backend.dao

import com.TmsBackend.Tms.Backend.models.dao.Employ
import com.TmsBackend.Tms.Backend.models.dao.UserAuth
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class AuthDao(private val jdbcTemplate: JdbcTemplate) {
    fun createEmploy(employ: Employ): Int {
        val sql = """
            INSERT INTO employ (name, email, contact_number, role, created_at)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
        """
        return jdbcTemplate.queryForObject(sql, Int::class.java,
            employ.name, employ.email, employ.contact_number, employ.role, employ.created_at)!!
    }

    fun createUserAuth(userAuth: UserAuth) {
        val sql = """
            INSERT INTO user_auth (id, email, name, password_hash)
            VALUES (?, ?, ?, ?)
        """
        jdbcTemplate.update(sql,
            userAuth.id, userAuth.email, userAuth.name, userAuth.password_hash)
    }

    fun findUserByEmail(email: String): Pair<Employ, UserAuth>? {
        val sql = """
            SELECT e.*, ua.password_hash, ua.refresh_token
            FROM employ e
            JOIN user_auth ua ON e.id = ua.id
            WHERE e.email = ?
        """
        return try {
            jdbcTemplate.queryForObject(sql, { rs, _ ->
                Pair(
                    Employ(
                        id = rs.getInt("id"),
                        name = rs.getString("name"),
                        email = rs.getString("email"),
                        contact_number = rs.getString("contact_number"),
                        role = rs.getString("role"),
                        created_at = rs.getLong("created_at")
                    ),
                    UserAuth(
                        id = rs.getInt("id"),
                        email = rs.getString("email"),
                        name = rs.getString("name"),
                        password_hash = rs.getString("password_hash"),
                        refresh_token = rs.getString("refresh_token")
                    )
                )
            }, email)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun updateRefreshToken(email: String, refreshToken: String?) {
        val sql = "UPDATE user_auth SET refresh_token = ? WHERE email = ?"
        jdbcTemplate.update(sql, refreshToken, email)
    }
}