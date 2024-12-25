package com.TmsBackend.Tms.Backend.models.dao

import java.util.*

data class Location(
    val id: String? = UUID.randomUUID().toString(), // UUID for unique identifier
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
    val pincode: String,
    val created_at: Long = System.currentTimeMillis() // Default to current epoch time in milliseconds
)
