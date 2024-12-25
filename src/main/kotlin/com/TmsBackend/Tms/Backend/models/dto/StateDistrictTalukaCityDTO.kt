package com.TmsBackend.Tms.Backend.models.dto

data class StateRequest(
    val state: String?
)

data class DistrictRequest(
    val state: String?,
    val district: String?
)

data class CityRequest(
    val state: String?,
    val district: String?,
    val taluka: String?
)

data class Location(
    val name: String,
    val districts: List<District>
)

data class District(
    val name: String,
    val talukas: List<Taluka>
)

data class Taluka(
    val name: String,
    val cities: List<String>
)

data class Locations(
    val locations: List<Location>
)