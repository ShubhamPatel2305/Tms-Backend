package com.TmsBackend.Tms.Backend.dao

import com.TmsBackend.Tms.Backend.models.dao.Party
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

@Component
class PartyDao(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        Party(
            id = rs.getString("id"),
            name = rs.getString("name"),
            pointOfContact = rs.getString("point_of_contact"),
            contactNo = rs.getString("contact_no"),
            email = rs.getString("email"),
            addressLine1 = rs.getString("address_line1"),
            addressLine2 = rs.getString("address_line2"),
            state = rs.getString("state"),
            district = rs.getString("district"),
            taluka = rs.getString("taluka"),
            city = rs.getString("city"),
            pincode = rs.getString("pincode")
        )
    }

    fun getAllParties(): List<Party> {
        val sql = "SELECT * FROM party"
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun addParty(party: Party): Party {
        val sql = """
            INSERT INTO party (id, name, point_of_contact, contact_no, email, address_line1, address_line2, state, district, taluka, city, pincode)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        jdbcTemplate.update(sql, party.id, party.name, party.pointOfContact, party.contactNo, party.email, party.addressLine1,
            party.addressLine2, party.state, party.district, party.taluka, party.city, party.pincode)
        return party
    }

    fun updateParty(party: Party): Party {
        val sql = """
            UPDATE party SET name = ?, point_of_contact = ?, contact_no = ?, email = ?, address_line1 = ?, address_line2 = ?, state = ?, 
            district = ?, taluka = ?, city = ?, pincode = ? WHERE id = ?
        """
        jdbcTemplate.update(sql, party.name, party.pointOfContact, party.contactNo, party.email, party.addressLine1,
            party.addressLine2, party.state, party.district, party.taluka, party.city, party.pincode, party.id)
        return party
    }

    fun deleteParty(id: String) {
        val sql = "DELETE FROM party WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }
}
