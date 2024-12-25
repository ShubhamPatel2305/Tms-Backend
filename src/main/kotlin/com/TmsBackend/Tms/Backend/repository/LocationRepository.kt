package com.TmsBackend.Tms.Backend.repository

import com.TmsBackend.Tms.Backend.dao.LocationDao
import com.TmsBackend.Tms.Backend.models.dao.Location
import org.springframework.stereotype.Repository

@Repository
class LocationRepository(private val locationDao: LocationDao) {

    fun findAll(): List<Location> {
        return try {
            locationDao.getAlllocation()
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching locations: ${ex.message}")
        }
    }

    fun save(location: Location): Location {
        return try {
            locationDao.addLocation(location)
        } catch (ex: Exception) {
            throw RuntimeException("Error saving location: ${ex.message}")
        }
    }

    fun update(location: Location): Location {
        return try {
            locationDao.updateLocation(location)
        } catch (ex: Exception) {
            throw RuntimeException("Error updating location: ${ex.message}")
        }
    }

    fun delete(id: String) {
        try {
            locationDao.deleteLocation(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting location with id $id: ${ex.message}")
        }
    }
}
