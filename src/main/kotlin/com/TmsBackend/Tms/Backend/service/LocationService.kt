package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dto.LocationDTO
import com.TmsBackend.Tms.Backend.repository.LocationRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getAllLocations(): List<LocationDTO> {
        return locationRepository.findAll().map { LocationDTO.fromDTO(it) }
    }

    fun addLocation(request: LocationDTO): LocationDTO {
        val location = request.toDTO(id = UUID.randomUUID().toString())
        return LocationDTO.fromDTO(locationRepository.save(location))
    }

    fun updateLocation(id: String, request: LocationDTO): LocationDTO {
        val location = request.toDTO(id)
        return LocationDTO.fromDTO(locationRepository.update(location))
    }

    fun deleteLocation(id: String) {
        locationRepository.delete(id)
    }
}
