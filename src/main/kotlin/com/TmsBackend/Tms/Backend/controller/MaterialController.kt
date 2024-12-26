package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.MaterialDTO
import com.TmsBackend.Tms.Backend.service.MaterialService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/v1/materials")
@CrossOrigin
class MaterialController(private val materialService: MaterialService) {

    @PostMapping("/list")
    fun getAllMaterials(): ResponseEntity<List<MaterialDTO>> {
        return try {
            val materials = materialService.getAllMaterials()
            ResponseEntity.ok(materials)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(listOf())
        }
    }

    @PostMapping("/create")
    fun addMaterial(@RequestBody request: MaterialDTO): ResponseEntity<MaterialDTO> {
        return try {
            val createdMaterial = materialService.addMaterial(request)
            ResponseEntity.status(201).body(createdMaterial)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @GetMapping("get/{id}")
    fun getMaterial(@PathVariable id: String): ResponseEntity<MaterialDTO> {
        return try {
            val material = materialService.getMaterialById(id)
            material?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @PostMapping("/update")
    fun updateMaterial(
        @RequestBody request: MaterialDTO
    ): ResponseEntity<MaterialDTO> {
        return try {
            val id = request.id!!
            val updatedMaterial = materialService.updateMaterial(id, request)
            ResponseEntity.ok(updatedMaterial)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMaterial(@PathVariable id: String): ResponseEntity<Void> {
        return try {
            materialService.deleteMaterial(id)
            ResponseEntity.noContent().build()
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}
