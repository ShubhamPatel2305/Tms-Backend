package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.*
import com.TmsBackend.Tms.Backend.service.StateDistrictTalukaCityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
class StateDistrictTalukaCityController(
    private val stateDistrictTalukaCityService: StateDistrictTalukaCityService
) {
    @GetMapping("/states/list")
    fun listStates(): List<String> {
        return stateDistrictTalukaCityService.listStates().toSet().toList()
    }

    @PostMapping("/districts/list")
    fun listDistricts(@RequestBody request: StateRequest): List<String> {
        return stateDistrictTalukaCityService.listDistricts(request).toSet().toList()
    }

    @PostMapping("/talukas/list")
    fun listTalukas(@RequestBody request: DistrictRequest): List<String> {
        return stateDistrictTalukaCityService.listTalukas(request).toSet().toList()
    }

    @PostMapping("/cities/list")
    fun listCities(@RequestBody request: CityRequest): List<String> {
        val cities = stateDistrictTalukaCityService.listCities(request)
        return cities.toSet().toList()
    }
}