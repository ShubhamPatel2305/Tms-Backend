package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.LocationDTO
import com.TmsBackend.Tms.Backend.service.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/v1/locations")
@CrossOrigin
class LocationController(private val locationService: LocationService) {

    @PostMapping("/list")
    fun getAllLocations(): ResponseEntity<List<LocationDTO>> {
        return try {
            val locations = locationService.getAllLocations()
            ResponseEntity.ok(locations)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(listOf())
        }
    }

    @PostMapping("/create")
    fun addLocation(@RequestBody request: LocationDTO): ResponseEntity<LocationDTO> {
        return try {
            val createdLocation = locationService.addLocation(request)
            ResponseEntity.status(201).body(createdLocation)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @GetMapping("get/{id}")
    fun getLocation(@PathVariable id: String): ResponseEntity<LocationDTO> {
        return try {
            val location = locationService.getLocationById(id)
            location?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }



    @PostMapping("/update")
    fun updateLocation(
        @RequestBody request: LocationDTO
    ): ResponseEntity<LocationDTO> {
        return try {
            val id=request.id!!
            val updatedLocation = locationService.updateLocation(id, request)
            ResponseEntity.ok(updatedLocation)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteLocation(@PathVariable id: String): ResponseEntity<Void> {
        return try {
            locationService.deleteLocation(id)
            ResponseEntity.noContent().build()
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}