package com.TmsBackend.Tms.Backend.models

import com.TmsBackend.Tms.Backend.dbentity.Location
import java.util.UUID

data class LocationRequest(
    val name: String,
    val pointOfContact: String,
    val contactNo: String,
    val email: String,
    val addressLine1: String,
    val addressLine2: String?,
    val state: String,
    val district: String,
    val taluka: String,
    val city: String,
    val pincode: String
) {
    fun toEntity(id: String): Location {
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
            pincode = pincode
        )
    }
}
