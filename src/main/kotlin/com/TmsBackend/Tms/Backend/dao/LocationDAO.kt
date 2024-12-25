package com.TmsBackend.Tms.Backend.dao


import com.TmsBackend.Tms.Backend.dbentity.Location
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

    fun getAlllocation(): List<Location> {
        val sql = "SELECT * FROM location"
        return jdbcTemplate.query(sql, rowMapper)
    }

    fun addLocation(location: Location): Location {
        val sql = """
            INSERT INTO location (id, name, point_of_contact, contact_no, email, address_line1, address_line2, state, district, taluka, city, pincode)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        jdbcTemplate.update(sql, location.id, location.name, location.pointOfContact, location.contactNo, location.email, location.addressLine1,
            location.addressLine2, location.state, location.district, location.taluka, location.city, location.pincode)
        return location
    }

    fun updateLocation(location: Location): Location {
        val sql = """
            UPDATE location SET name = ?, point_of_contact = ?, contact_no = ?, email = ?, address_line1 = ?, address_line2 = ?, state = ?, 
            district = ?, taluka = ?, city = ?, pincode = ? WHERE id = ?
        """
        jdbcTemplate.update(sql, location.name, location.pointOfContact, location.contactNo, location.email, location.addressLine1,
            location.addressLine2, location.state, location.district, location.taluka, location.city, location.pincode, location.id)
        return location
    }

    fun deleteLocation(id: String) {
        val sql = "DELETE FROM location WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }
}


