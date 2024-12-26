package com.TmsBackend.Tms.Backend.models.dao

data class Dc(
    val id: String,          // Primary Key
    val do_id: String,        // Foreign Key
    val vehicle_id: String,   // Foreign Key
    val driving_license_number: String,
    val created_at: Long,
    val updated_at: Long,
    val status: String
)