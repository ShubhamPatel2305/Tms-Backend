package com.TmsBackend.Tms.Backend.models.dao

import java.util.UUID

data class Vehicle(
    val id: String?=UUID.randomUUID().toString(),
    val vehicle_number: String,
    val rc_book:String,
    val transport_company_id: String
)