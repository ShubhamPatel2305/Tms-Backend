package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.PartyDTO
import com.TmsBackend.Tms.Backend.service.PartyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/parties")
@CrossOrigin
class PartyController(private val partyService: PartyService) {

    @GetMapping
    fun getAllParties(): ResponseEntity<List<PartyDTO>> {
        val parties = partyService.getAllParties()
        return ResponseEntity.ok(parties)
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
