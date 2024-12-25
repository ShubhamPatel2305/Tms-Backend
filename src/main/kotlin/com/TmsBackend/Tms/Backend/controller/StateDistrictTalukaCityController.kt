package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.*
import com.TmsBackend.Tms.Backend.service.StateDistrictTalukaCityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin
class StateDistrictTalukaCityController(
    private val stateDistrictTalukaCityService: StateDistrictTalukaCityService
) {
    @GetMapping("/states/list")
    fun listStates(): List<String> {
        return stateDistrictTalukaCityService.listStates()
    }

    @PostMapping("/districts/list")
    fun listDistricts(@RequestBody request: StateRequest): List<String> {
        return stateDistrictTalukaCityService.listDistricts(request)
    }

    @PostMapping("/talukas/list")
    fun listTalukas(@RequestBody request: DistrictRequest): List<String> {
        return stateDistrictTalukaCityService.listTalukas(request)
    }

    @PostMapping("/cities/list")
    fun listCities(@RequestBody request: CityRequest): List<String> {
        return stateDistrictTalukaCityService.listCities(request)
    }
}