package com.TmsBackend.Tms.Backend.models.dao

import java.util.*

data class Unit (
    val id: String?= UUID.randomUUID().toString(),
    val name: String,
)