package com.TmsBackend.Tms.Backend.dao

import com.TmsBackend.Tms.Backend.models.dao.Unit
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

@Component
class UnitDao(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        Unit(
            id = rs.getString("id"),
            name = rs.getString("name")
        )
    }

    fun getAllUnits(): List<Unit> {
        return try {
            val sql = "SELECT * FROM unit"
            jdbcTemplate.query(sql, rowMapper)
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching all units: ${ex.message}")
        }
    }

    fun getUnitById(id: String): Unit? {
        return try {
            val sql = "SELECT * FROM unit WHERE id = ?"
            jdbcTemplate.query(sql, rowMapper, id).firstOrNull()
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching unit with id $id: ${ex.message}")
        }
    }

    fun addUnit(unit: Unit): Unit {
        return try {
            val sql = """
                INSERT INTO unit (id, name)
                VALUES (?, ?)
            """
            jdbcTemplate.update(sql, unit.id, unit.name)
            unit
        } catch (ex: Exception) {
            throw RuntimeException("Error adding unit: ${ex.message}")
        }
    }

    fun updateUnit(unit: Unit): Unit {
        return try {
            val sql = """
                UPDATE unit SET name = ? WHERE id = ?
            """
            jdbcTemplate.update(sql, unit.name, unit.id)
            unit
        } catch (ex: Exception) {
            throw RuntimeException("Error updating unit: ${ex.message}")
        }
    }

    fun deleteUnit(id: String) {
        try {
            val sql = "DELETE FROM unit WHERE id = ?"
            jdbcTemplate.update(sql, id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting unit with id $id: ${ex.message}")
        }
    }
}
