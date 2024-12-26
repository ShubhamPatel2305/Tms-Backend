package com.TmsBackend.Tms.Backend.repository

import com.TmsBackend.Tms.Backend.dao.UnitDao
import com.TmsBackend.Tms.Backend.models.dao.Unit
import org.springframework.stereotype.Repository

@Repository
class UnitRepository(private val unitDao: UnitDao) {

    fun findById(id: String): Unit? {
        return try {
            unitDao.getUnitById(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching unit with id $id: ${ex.message}")
        }
    }

    fun findAll(): List<Unit> {
        return try {
            unitDao.getAllUnits()
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching units: ${ex.message}")
        }
    }

    fun save(unit: Unit): Unit {
        return try {
            unitDao.addUnit(unit)
        } catch (ex: Exception) {
            throw RuntimeException("Error saving unit: ${ex.message}")
        }
    }

    fun update(unit: Unit): Unit {
        return try {
            unitDao.updateUnit(unit)
        } catch (ex: Exception) {
            throw RuntimeException("Error updating unit: ${ex.message}")
        }
    }

    fun delete(id: String) {
        try {
            unitDao.deleteUnit(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting unit with id $id: ${ex.message}")
        }
    }
}
