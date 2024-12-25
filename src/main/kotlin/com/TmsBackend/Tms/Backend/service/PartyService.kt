package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dto.LocationDTO
import com.TmsBackend.Tms.Backend.models.dto.PartyDTO
import com.TmsBackend.Tms.Backend.repository.PartyRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PartyService(private val partyRepository: PartyRepository) {

    fun getAllParties(): List<PartyDTO> {
        return partyRepository.findAll().map { PartyDTO.fromDTO(it) }
    }

    fun addParty(request: PartyDTO): PartyDTO {
        val party = request.toDTO(id = UUID.randomUUID().toString())
        val partyWithCurrentTime = party.copy(created_at = System.currentTimeMillis())
        PartyDTO.fromDTO(partyRepository.save(partyWithCurrentTime))
        return PartyDTO.fromDTO(partyRepository.save(party))
    }

    fun updateParty(id: String, request: PartyDTO): PartyDTO {
        val party = request.toDTO(id)
        return PartyDTO.fromDTO(partyRepository.update(party))
    }

    fun deleteParty(id: String) {
        partyRepository.delete(id)
    }
}
