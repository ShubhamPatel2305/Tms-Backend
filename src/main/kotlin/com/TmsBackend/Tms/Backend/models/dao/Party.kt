package com.TmsBackend.Tms.Backend.models.dao

import java.util.*

data class Party(
    val id: String? = UUID.randomUUID().toString(),
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
)
