package com.TmsBackend.Tms.Backend.models.dao

data class DoItem(
    val id: String,          // Primary Key
    val do_id: String,
    val state:String,
    val district: String,
    val taluka: String,
    val city: String,
    val location_id: String,  // Foreign Key
    val material_id: String,
    val quantity: Int,
    val unit_id: String,      // Foreign Key
    val deadline: Long,
    val pending: Int,
    val ongoing: Int,
    val status: String,
    val created_at: Long,
    val updated_at: Long
)