package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.UnitDTO
import com.TmsBackend.Tms.Backend.service.UnitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/v1/units")
@CrossOrigin
class UnitController(private val unitService: UnitService) {

    @PostMapping("/list")
    fun getAllUnits(): ResponseEntity<List<UnitDTO>> {
        return try {
            val units = unitService.getAllUnits()
            ResponseEntity.ok(units)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(listOf())
        }
    }

    @PostMapping("/create")
    fun addUnit(@RequestBody request: UnitDTO): ResponseEntity<UnitDTO> {
        return try {
            val createdUnit = unitService.addUnit(request)
            ResponseEntity.status(201).body(createdUnit)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @GetMapping("get/{id}")
    fun getUnit(@PathVariable id: String): ResponseEntity<UnitDTO> {
        return try {
            val unit = unitService.getUnitById(id)
            unit?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @PostMapping("/update")
    fun updateUnit(
        @RequestBody request: UnitDTO
    ): ResponseEntity<UnitDTO> {
        return try {
            val id = request.id!!
            val updatedUnit = unitService.updateUnit(id, request)
            ResponseEntity.ok(updatedUnit)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUnit(@PathVariable id: String): ResponseEntity<Void> {
        return try {
            unitService.deleteUnit(id)
            ResponseEntity.noContent().build()
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}
