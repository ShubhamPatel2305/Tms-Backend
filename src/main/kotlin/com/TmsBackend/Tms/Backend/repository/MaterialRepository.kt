package com.TmsBackend.Tms.Backend.repository

import com.TmsBackend.Tms.Backend.dao.MaterialDao
import com.TmsBackend.Tms.Backend.models.dao.Material
import org.springframework.stereotype.Repository

@Repository
class MaterialRepository(private val materialDao: MaterialDao) {

    fun findById(id: String): Material? {
        return try {
            materialDao.getMaterialById(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching material with id $id: ${ex.message}")
        }
    }

    fun findAll(): List<Material> {
        return try {
            materialDao.getAllMaterials()
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching materials: ${ex.message}")
        }
    }

    fun save(material: Material): Material {
        return try {
            materialDao.addMaterial(material)
        } catch (ex: Exception) {
            throw RuntimeException("Error saving material: ${ex.message}")
        }
    }

    fun update(material: Material): Material {
        return try {
            materialDao.updateMaterial(material)
        } catch (ex: Exception) {
            throw RuntimeException("Error updating material: ${ex.message}")
        }
    }

    fun delete(id: String) {
        try {
            materialDao.deleteMaterial(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting material with id $id: ${ex.message}")
        }
    }
}
