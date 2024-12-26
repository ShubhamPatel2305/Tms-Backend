package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dto.MaterialDTO
import com.TmsBackend.Tms.Backend.repository.MaterialRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MaterialService(private val materialRepository: MaterialRepository) {

    fun getAllMaterials(): List<MaterialDTO> {
        return try {
            materialRepository.findAll().map { MaterialDTO.fromDAO(it) }
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching materials: ${ex.message}")
        }
    }

    fun addMaterial(request: MaterialDTO): MaterialDTO {
        return try {
            val material = request.toDAO(id = UUID.randomUUID().toString())
            MaterialDTO.fromDAO(materialRepository.save(material))
        } catch (ex: Exception) {
            throw RuntimeException("Error adding material: ${ex.message}")
        }
    }

    fun getMaterialById(id: String): MaterialDTO? {
        return try {
            materialRepository.findById(id)?.let { MaterialDTO.fromDAO(it) }
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching material with id $id: ${ex.message}")
        }
    }

    fun updateMaterial(id: String, request: MaterialDTO): MaterialDTO {
        return try {
            val material = request.toDAO(id)
            MaterialDTO.fromDAO(materialRepository.update(material))
        } catch (ex: Exception) {
            throw RuntimeException("Error updating material with id $id: ${ex.message}")
        }
    }

    fun deleteMaterial(id: String) {
        try {
            materialRepository.delete(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting material with id $id: ${ex.message}")
        }
    }
}
