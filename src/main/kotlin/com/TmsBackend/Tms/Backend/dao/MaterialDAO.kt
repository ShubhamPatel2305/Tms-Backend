package com.TmsBackend.Tms.Backend.dao

import com.TmsBackend.Tms.Backend.models.dao.Material
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

@Component
class MaterialDao(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        Material(
            id = rs.getString("id"),
            name = rs.getString("name")
        )
    }

    fun getAllMaterials(): List<Material> {
        return try {
            val sql = "SELECT * FROM material"
            jdbcTemplate.query(sql, rowMapper)
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching all materials: ${ex.message}")
        }
    }

    fun getMaterialById(id: String): Material? {
        return try {
            val sql = "SELECT * FROM material WHERE id = ?"
            jdbcTemplate.query(sql, rowMapper, id).firstOrNull()
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching material with id $id: ${ex.message}")
        }
    }

    fun addMaterial(material: Material): Material {
        return try {
            val sql = "INSERT INTO material (id, name) VALUES (?, ?)"
            jdbcTemplate.update(sql, material.id, material.name)
            material
        } catch (ex: Exception) {
            throw RuntimeException("Error adding material: ${ex.message}")
        }
    }

    fun updateMaterial(material: Material): Material {
        return try {
            val sql = "UPDATE material SET name = ? WHERE id = ?"
            jdbcTemplate.update(sql, material.name, material.id)
            material
        } catch (ex: Exception) {
            throw RuntimeException("Error updating material: ${ex.message}")
        }
    }

    fun deleteMaterial(id: String) {
        try {
            val sql = "DELETE FROM material WHERE id = ?"
            jdbcTemplate.update(sql, id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting material with id $id: ${ex.message}")
        }
    }
}
