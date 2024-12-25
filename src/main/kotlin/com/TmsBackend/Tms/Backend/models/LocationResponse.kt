package com.TmsBackend.Tms.Backend.models

import com.TmsBackend.Tms.Backend.dbentity.Location
import java.util.UUID


data class LocationResponse(
    val id: String?,
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
    companion object {
        fun fromEntity(location: Location): LocationResponse {
            return LocationResponse(
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
                pincode = location.pincode
            )
        }
    }
}
