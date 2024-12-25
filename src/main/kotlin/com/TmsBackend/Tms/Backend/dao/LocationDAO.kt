package com.TmsBackend.Tms.Backend.dao

import com.TmsBackend.Tms.Backend.models.dao.Location
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

@Component
class LocationDao(private val jdbcTemplate: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        Location(
            id = rs.getString("id"),
            name = rs.getString("name"),
            pointOfContact = rs.getString("point_of_contact"),
            contactNo = rs.getString("contact_number"),
            email = rs.getString("email"),
            addressLine1 = rs.getString("address_line1"),
            addressLine2 = rs.getString("address_line2"),
            state = rs.getString("state"),
            district = rs.getString("district"),
            taluka = rs.getString("taluka"),
            city = rs.getString("city"),
            pincode = rs.getString("pincode"),
            created_at = rs.getLong("created_at")
        )
    }

    fun getAlllocation(): List<Location> {
        return try {
            val sql = "SELECT * FROM location"
            jdbcTemplate.query(sql, rowMapper)
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching all locations: ${ex.message}")
        }
    }

    fun getLocationById(id: String): Location? {
        return try {
            val sql = "SELECT * FROM location WHERE id = ?"
            jdbcTemplate.query(sql, rowMapper, id).firstOrNull()
        } catch (ex: Exception) {
            throw RuntimeException("Error fetching location with id $id: ${ex.message}")
        }
    }

    fun addLocation(location: Location): Location {
        return try {
            val sql = """
                INSERT INTO location (id, name, point_of_contact, contact_number, email, address_line1, address_line2, state, district, taluka, city, pincode, created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """
            jdbcTemplate.update(sql, location.id, location.name, location.pointOfContact, location.contactNo, location.email, location.addressLine1,
                location.addressLine2, location.state, location.district, location.taluka, location.city, location.pincode, location.created_at)
            location
        } catch (ex: Exception) {
            throw RuntimeException("Error adding location: ${ex.message}")
        }
    }

    fun updateLocation(location: Location): Location {
        return try {
            val sql = """
                UPDATE location SET name = ?, point_of_contact = ?, contact_number = ?, email = ?, address_line1 = ?, address_line2 = ?, state = ?, 
                district = ?, taluka = ?, city = ?, pincode = ? WHERE id = ?
            """
            jdbcTemplate.update(sql, location.name, location.pointOfContact, location.contactNo, location.email, location.addressLine1,
                location.addressLine2, location.state, location.district, location.taluka, location.city, location.pincode, location.id)
            location
        } catch (ex: Exception) {
            throw RuntimeException("Error updating location: ${ex.message}")
        }
    }

    fun deleteLocation(id: String) {
        try {
            val sql = "DELETE FROM location WHERE id = ?"
            jdbcTemplate.update(sql, id)
        } catch (ex: Exception) {
            throw RuntimeException("Error deleting location with id $id: ${ex.message}")
        }
    }
}
