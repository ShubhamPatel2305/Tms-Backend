package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.LocationRequest
import com.TmsBackend.Tms.Backend.models.LocationResponse
import com.TmsBackend.Tms.Backend.repository.LocationRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getAllLocations(): List<LocationResponse> {
        return locationRepository.findAll().map { LocationResponse.fromEntity(it) }
    }

    fun addLocation(request: LocationRequest): LocationResponse {
        val location = request.toEntity(id = UUID.randomUUID().toString())
        return LocationResponse.fromEntity(locationRepository.save(location))
    }

    fun updateLocation(id: String, request: LocationRequest): LocationResponse {
        val location = request.toEntity(id)
        return LocationResponse.fromEntity(locationRepository.update(location))
    }

    fun deleteLocation(id: String) {
        locationRepository.delete(id)
    }
}
