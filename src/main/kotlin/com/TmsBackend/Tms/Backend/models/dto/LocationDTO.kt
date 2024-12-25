package com.TmsBackend.Tms.Backend.models.dto

import com.TmsBackend.Tms.Backend.models.dao.Location

data class LocationDTO(
    val id: String?,
    val name: String,
    val pointOfContact: String?,
    val contactNo: String?,
    val email: String?,
    val addressLine1: String?,
    val addressLine2: String?,
    val state: String?,
    val district: String,
    val taluka: String,
    val city: String?,
    val pincode: String?,
    val created_at: Long // Removed default value
) {
    companion object {
        fun fromDTO(location: Location): LocationDTO {
            return LocationDTO(
                id = location.id,
                name = location.name,
                pointOfContact = location.pointOfContact,
                contactNo = location.contactNo,
                email = location.email,
                addressLine1 = location.addressLine1,
                addressLine2 = location.addressLine2,
                state = location.state,
                district = location.district,
                taluka = location.taluka,
                city = location.city,
                pincode = location.pincode,
                created_at = location.created_at
            )
        }
    }

    fun toDTO(id: String): Location {
        return Location(
            id = id,
            name = name,
            pointOfContact = pointOfContact,
            contactNo = contactNo,
            email = email,
            addressLine1 = addressLine1,
            addressLine2 = addressLine2,
            state = state,
            district = district,
            taluka = taluka,
            city = city,
            pincode = pincode,
            created_at = created_at // backend will manage this field
        )
    }
}
