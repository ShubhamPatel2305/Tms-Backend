package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dto.UnitDTO
import com.TmsBackend.Tms.Backend.repository.UnitRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UnitService(private val unitRepository: UnitRepository) {

    fun getAllUnits(): List<UnitDTO> {
        return try {
            unitRepository.findAll().map { UnitDTO.fromDAO(it) }
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching units: ${ex.message}")
        }
    }

    fun addUnit(request: UnitDTO): UnitDTO {
        return try {
            val unit = request.toDAO(id = UUID.randomUUID().toString())
            UnitDTO.fromDAO(unitRepository.save(unit))
        } catch (ex: Exception) {
            throw RuntimeException("Error adding unit: ${ex.message}")
        }
    }

    fun getUnitById(id: String): UnitDTO? {
        return try {
            unitRepository.findById(id)?.let { UnitDTO.fromDAO(it) }
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching unit with id $id: ${ex.message}")
        }
    }

    fun updateUnit(id: String, request: UnitDTO): UnitDTO {
        return try {
            val unit = request.toDAO(id)
            UnitDTO.fromDAO(unitRepository.update(unit))
        } catch (ex: Exception) {
            throw RuntimeException("Error updating unit with id $id: ${ex.message}")
        }
    }

    fun deleteUnit(id: String) {
        try {
            unitRepository.delete(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting unit with id $id: ${ex.message}")
        }
    }
}
