package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dto.LocationDTO
import com.TmsBackend.Tms.Backend.repository.LocationRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getAllLocations(): List<LocationDTO> {
        return try {
            locationRepository.findAll().map { LocationDTO.fromDTO(it) }
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching locations: ${ex.message}")
        }
    }

    fun addLocation(request: LocationDTO): LocationDTO {
        return try {
            val location = request.toDTO(id = UUID.randomUUID().toString())
            // Override created_at to the current epoch time
            val locationWithCurrentTime = location.copy(created_at = System.currentTimeMillis())
            LocationDTO.fromDTO(locationRepository.save(locationWithCurrentTime))
        } catch (ex: Exception) {
            throw RuntimeException("Error adding location: ${ex.message}")
        }
    }

    fun updateLocation(id: String, request: LocationDTO): LocationDTO {
        return try {
            val location = request.toDTO(id)
            LocationDTO.fromDTO(locationRepository.update(location))
        } catch (ex: Exception) {
            throw RuntimeException("Error updating location with id $id: ${ex.message}")
        }
    }

    fun deleteLocation(id: String) {
        try {
            locationRepository.delete(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting location with id $id: ${ex.message}")
        }
    }
}
