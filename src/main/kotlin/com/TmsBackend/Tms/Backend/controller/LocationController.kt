package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.LocationRequest
import com.TmsBackend.Tms.Backend.models.LocationResponse
import com.TmsBackend.Tms.Backend.service.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/location")
class LocationController(private val locationService: LocationService) {

    @GetMapping
    fun getAllLocations(): ResponseEntity<List<LocationResponse>> {
        val locations = locationService.getAllLocations()
        return ResponseEntity.ok(locations)
    }

    @PostMapping
    fun addLocation(@RequestBody request: LocationRequest): ResponseEntity<LocationResponse> {
        val createdLocation = locationService.addLocation(request)
        return ResponseEntity.status(201).body(createdLocation)
    }

    @PutMapping("/{id}")
    fun updateLocation(
        @PathVariable id: String,
        @RequestBody request: LocationRequest
    ): ResponseEntity<LocationResponse> {
        val updatedLocation = locationService.updateLocation(id, request)
        return ResponseEntity.ok(updatedLocation)
    }

    @DeleteMapping("/{id}")
    fun deleteLocation(@PathVariable id: String): ResponseEntity<Void> {
        locationService.deleteLocation(id)
        return ResponseEntity.noContent().build()
    }
}
