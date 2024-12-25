package com.TmsBackend.Tms.Backend.repository

import com.TmsBackend.Tms.Backend.dao.LocationDao
import com.TmsBackend.Tms.Backend.models.dao.Location
import org.springframework.stereotype.Repository

@Repository
class LocationRepository(private val locationDao: LocationDao) {

    fun findAll(): List<Location> {
        return locationDao.getAlllocation()
    }

    fun save(location: Location): Location {
        return locationDao.addLocation(location)
    }

    fun update(location: Location): Location {
        return locationDao.updateLocation(location)
    }

    fun delete(id: String) {
        locationDao.deleteLocation(id)
    }
}
