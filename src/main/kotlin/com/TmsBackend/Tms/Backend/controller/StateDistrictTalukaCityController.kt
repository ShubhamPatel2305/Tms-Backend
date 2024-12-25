package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.*
import com.TmsBackend.Tms.Backend.service.StateDistrictTalukaCityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/location2")
class StateDistrictTalukaCityController(
    private val stateDistrictTalukaCityService: StateDistrictTalukaCityService
) {
    @GetMapping("/states")
    fun listStates(): List<String> {
        return stateDistrictTalukaCityService.listStates()
    }

    @PostMapping("/districts")
    fun listDistricts(@RequestBody request: StateRequest): List<String> {
        return stateDistrictTalukaCityService.listDistricts(request)
    }

    @PostMapping("/talukas")
    fun listTalukas(@RequestBody request: DistrictRequest): List<String> {
        return stateDistrictTalukaCityService.listTalukas(request)
    }

    @PostMapping("/cities")
    fun listCities(@RequestBody request: CityRequest): List<String> {
        return stateDistrictTalukaCityService.listCities(request)
    }
}
