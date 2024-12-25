package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.PartyDTO
import com.TmsBackend.Tms.Backend.service.PartyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/parties")
@CrossOrigin
class PartyController(private val partyService: PartyService) {

    @PostMapping("/list")
    fun getAllParties(): ResponseEntity<List<PartyDTO>> {
        val parties = partyService.getAllParties()
        return ResponseEntity.ok(parties)
    }

    @GetMapping("/get/{id}")
    fun getParty(@PathVariable id: String): ResponseEntity<PartyDTO> {
        return try {
            val party = partyService.getPartyById(id)
            party?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null)
        }
    }

    @PostMapping("/create")
    fun addParty(@RequestBody request: PartyDTO): ResponseEntity<PartyDTO> {
        val createdParty = partyService.addParty(request)
        return ResponseEntity.status(201).body(createdParty)
    }

    @PutMapping("/{id}")
    fun updateParty(
        @PathVariable id: String,
        @RequestBody request: PartyDTO
    ): ResponseEntity<PartyDTO> {
        val updatedParty = partyService.updateParty(id, request)
        return ResponseEntity.ok(updatedParty)
    }

    @DeleteMapping("/{id}")
    fun deleteParty(@PathVariable id: String): ResponseEntity<Void> {
        partyService.deleteParty(id)
        return ResponseEntity.noContent().build()
    }
}
