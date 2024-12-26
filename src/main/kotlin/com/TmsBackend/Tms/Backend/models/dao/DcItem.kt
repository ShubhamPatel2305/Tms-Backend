package com.TmsBackend.Tms.Backend.models.dao

data class DcItem(
    val id: String,               // Primary Key
    val dc_id: String,             // Foreign Key
    val doItem_id: String,         // Foreign Key
    val transported_quantity: Int
) 