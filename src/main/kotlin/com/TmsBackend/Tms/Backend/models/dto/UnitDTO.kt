package com.TmsBackend.Tms.Backend.models.dto

import com.TmsBackend.Tms.Backend.models.dao.Unit

data class UnitDTO(
    val id: String?,
    val name: String
) {
    companion object {
        fun fromDAO(unit: Unit): UnitDTO {
            return UnitDTO(
                id = unit.id,
                name = unit.name
            )
        }
    }

    fun toDAO(id: String): Unit {
        return Unit(
            id = id,
            name = name
        )
    }
}
