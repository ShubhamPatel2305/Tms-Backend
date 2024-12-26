package com.TmsBackend.Tms.Backend.models.dto

import com.TmsBackend.Tms.Backend.models.dao.Material

data class MaterialDTO(
    val id: String?,
    val name: String
) {
    companion object {
        fun fromDAO(material: Material): MaterialDTO {
            return MaterialDTO(
                id = material.id,
                name = material.name
            )
        }
    }

    fun toDAO(id: String): Material {
        return Material(
            id = id,
            name = name
        )
    }
}
