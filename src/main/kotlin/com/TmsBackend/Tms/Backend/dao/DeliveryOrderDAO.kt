package com.TmsBackend.Tms.Backend.dao

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeliveryOrderDao(private val jdbcTemplate: JdbcTemplate) {

    fun createDo(
        id: String,
        doId: String,
        partyId: String,
        unitId: String,
        total: Int,
        pending: Int,
        ongoing: Int,
        status: String,
        createdAt: Long,
        updatedAt: Long
    ): Boolean {
        val sql = """
            INSERT INTO "do" (id, do_id, party_id, unit_id, total, pending, ongoing, status, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return try {
            jdbcTemplate.update(sql, id, doId, partyId, unitId, total, pending, ongoing, status, createdAt, updatedAt) > 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to create DO: ${e.message}")
        }
    }

    fun createDoItem(
        id: String,
        doId: String,
        state: String,
        district: String,
        taluka: String,
        city: String,
        locationId: String,
        materialId: String,
        quantity: Int,
        unitId: String,
        deadline: Long,
        pending: Int,
        ongoing: Int,
        status: String,
        createdAt: Long,
        updatedAt: Long
    ): Boolean {
        val sql = """
            INSERT INTO do_item (id, do_id, state, district, taluka, city, location_id, material_id, quantity, unit_id, 
                               deadline, pending, ongoing, status, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return try {
            jdbcTemplate.update(sql, id, doId, state, district, taluka, city, locationId, materialId, quantity, unitId,
                deadline, pending, ongoing, status, createdAt, updatedAt) > 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to create DO item: ${e.message}")
        }
    }

    fun findDoById(id: String): Map<String, Any>? {
        val sql = """
            SELECT * FROM "do" WHERE id = ?
        """.trimIndent()

        return try {
            jdbcTemplate.queryForMap(sql, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        } catch (e: Exception) {
            throw IllegalStateException("Failed to fetch DO: ${e.message}")
        }
    }

    fun updateDo(
        id: String,
        doId: String,
        partyId: String,
        unitId: String,
        updatedAt: Long
    ): Boolean {
        val sql = """
            UPDATE "do"
            SET do_id = ?, party_id = ?, unit_id = ?, updated_at = ?
            WHERE id = ?
        """.trimIndent()

        return try {
            jdbcTemplate.update(sql, doId, partyId, unitId, updatedAt, id) > 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to update DO: ${e.message}")
        }
    }

    fun updateDoTotals(
        id: String,
        total: Int,
        pending: Int,
        ongoing: Int,
        updatedAt: Long
    ): Boolean {
        val sql = """
            UPDATE "do"
            SET total = ?, pending = ?, ongoing = ?, updated_at = ?
            WHERE id = ?
        """.trimIndent()

        return try {
            jdbcTemplate.update(sql, total, pending, ongoing, updatedAt, id) > 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to update DO totals: ${e.message}")
        }
    }

    fun deleteDoItems(doId: String): Boolean {
        val sql = """
            DELETE FROM do_item WHERE do_id = ?
        """.trimIndent()

        return try {
            jdbcTemplate.update(sql, doId) >= 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to delete DO items: ${e.message}")
        }
    }
}