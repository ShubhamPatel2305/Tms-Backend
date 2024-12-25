package com.TmsBackend.Tms.Backend.models.dto

import com.TmsBackend.Tms.Backend.models.dao.Party

data class PartyDTO(
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
        fun fromDTO(party: Party): PartyDTO {
            return PartyDTO(
                id = party.id,
                name = party.name,
                pointOfContact = party.pointOfContact,
                contactNo = party.contactNo,
                email = party.email,
                addressLine1 = party.addressLine1,
                addressLine2 = party.addressLine2,
                state = party.state,
                district = party.district,
                taluka = party.taluka,
                city = party.city,
                pincode = party.pincode
            )
        }
    }

    fun toDTO(id: String): Party {
        return Party(
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
