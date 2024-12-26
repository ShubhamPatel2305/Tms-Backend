package com.TmsBackend.Tms.Backend.models.dao

import java.util.UUID

data class TransportCompany(
    val id: String?=UUID.randomUUID().toString(),
    val name: String
)