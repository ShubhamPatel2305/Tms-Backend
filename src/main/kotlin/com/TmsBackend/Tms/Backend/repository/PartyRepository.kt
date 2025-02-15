package com.TmsBackend.Tms.Backend.repository

import com.TmsBackend.Tms.Backend.dao.PartyDao
import com.TmsBackend.Tms.Backend.models.dao.Party
import org.springframework.stereotype.Repository

@Repository
class PartyRepository(private val partyDao: PartyDao) {

    fun findAll(): List<Party> {
        return partyDao.getAllParties()
    }

    fun findById(id: String): Party? {
        return try {
            partyDao.getPartyById(id)
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching party with id $id: ${ex.message}")
        }
    }

    fun save(party: Party): Party {
        return partyDao.addParty(party)
    }

    fun update(party: Party): Party {
        return partyDao.updateParty(party)
    }

    fun delete(id: String) {
        partyDao.deleteParty(id)
    }
}
