package com.TmsBackend.Tms.Backend.models.dao

data class Do (
    val id: String,          // Primary Key
    val do_id: String,
    val party_id: String,   // Foreign Key
    val pending: Int,
    val ongoing: Int,
    val unit: String,
    val status: String,
    val created_at: Long,
    val updated_at: Long
)