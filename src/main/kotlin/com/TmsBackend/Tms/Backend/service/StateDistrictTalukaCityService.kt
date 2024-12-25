package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dto.*
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

@Service
class StateDistrictTalukaCityService() {
    fun sayHello(): List<String> {
        return listOf("hello")
    }
    private val locations: Locations

    init {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        val resource = ClassPathResource("DistrictTalukaCityMapping.yaml")
        locations = mapper.readValue(resource.inputStream)
    }

    fun listStates(): List<String> {
        return locations.locations.map { location -> location.name }
    }

    fun listDistricts(stateRequest: StateRequest): List<String> {
        return when (stateRequest.state) {
            null -> locations.locations.flatMap { location ->
                location.districts.map { district -> district.name }
            }
            else -> locations.locations
                .find { location -> location.name == stateRequest.state }
                ?.districts
                ?.map { district -> district.name }
                ?: emptyList()
        }
    }

    fun listTalukas(request: DistrictRequest): List<String> {
        return when {
            // Both state and district are null - return all talukas
            request.state == null && request.district == null -> {
                locations.locations.flatMap { location ->
                    location.districts.flatMap { district ->
                        district.talukas.map { taluka -> taluka.name }
                    }
                }
            }
            // Only state is provided - return all talukas in that state
            request.state != null && request.district == null -> {
                locations.locations
                    .find { location -> location.name == request.state }
                    ?.districts
                    ?.flatMap { district ->
                        district.talukas.map { taluka -> taluka.name }
                    }
                    ?: emptyList()
            }
            // Only district is provided - return all talukas in that district across all states
            request.state == null && request.district != null -> {
                locations.locations.flatMap { location ->
                    location.districts
                        .filter { district -> district.name == request.district }
                        .flatMap { district ->
                            district.talukas.map { taluka -> taluka.name }
                        }
                }
            }
            // Both state and district are provided
            else -> {
                locations.locations
                    .find { location -> location.name == request.state }
                    ?.districts
                    ?.find { district -> district.name == request.district }
                    ?.talukas
                    ?.map { taluka -> taluka.name }
                    ?: emptyList()
            }
        }
    }

    fun listCities(request: CityRequest): List<String> {
        return when {
            // All parameters are null - return all cities
            request.state == null && request.district == null && request.taluka == null -> {
                locations.locations.flatMap { location ->
                    location.districts.flatMap { district ->
                        district.talukas.flatMap { taluka -> taluka.cities }
                    }
                }
            }
            // Only state is provided
            request.state != null && request.district == null && request.taluka == null -> {
                locations.locations
                    .find { location -> location.name == request.state }
                    ?.districts
                    ?.flatMap { district ->
                        district.talukas.flatMap { taluka -> taluka.cities }
                    }
                    ?: emptyList()
            }
            // Only district is provided
            request.state == null && request.district != null && request.taluka == null -> {
                locations.locations.flatMap { location ->
                    location.districts
                        .filter { district -> district.name == request.district }
                        .flatMap { district ->
                            district.talukas.flatMap { taluka -> taluka.cities }
                        }
                }
            }
            // Only taluka is provided
            request.state == null && request.district == null && request.taluka != null -> {
                locations.locations.flatMap { location ->
                    location.districts.flatMap { district ->
                        district.talukas
                            .filter { taluka -> taluka.name == request.taluka }
                            .flatMap { taluka -> taluka.cities }
                    }
                }
            }
            // State and district are provided
            request.state != null && request.district != null && request.taluka == null -> {
                locations.locations
                    .find { location -> location.name == request.state }
                    ?.districts
                    ?.find { district -> district.name == request.district }
                    ?.talukas
                    ?.flatMap { taluka -> taluka.cities }
                    ?: emptyList()
            }
            // State and taluka are provided
            request.state != null && request.district == null && request.taluka != null -> {
                locations.locations
                    .find { location -> location.name == request.state }
                    ?.districts
                    ?.flatMap { district ->
                        district.talukas
                            .filter { taluka -> taluka.name == request.taluka }
                            .flatMap { taluka -> taluka.cities }
                    }
                    ?: emptyList()
            }
            // District and taluka are provided
            request.state == null && request.district != null && request.taluka != null -> {
                locations.locations.flatMap { location ->
                    location.districts
                        .filter { district -> district.name == request.district }
                        .flatMap { district ->
                            district.talukas
                                .filter { taluka -> taluka.name == request.taluka }
                                .flatMap { taluka -> taluka.cities }
                        }
                }
            }
            // All parameters are provided
            else -> {
                locations.locations
                    .find { location -> location.name == request.state }
                    ?.districts
                    ?.find { district -> district.name == request.district }
                    ?.talukas
                    ?.filter { taluka -> taluka.name == request.taluka }
                    ?.flatMap { taluka -> taluka.cities }
                    ?: emptyList()
            }
        }
    }
}