package com.TmsBackend.Tms.Backend.dao

import com.TmsBackend.Tms.Backend.models.dao.DeliveryOrder
import com.TmsBackend.Tms.Backend.models.dao.DeliveryOrderItem
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeliveryOrderDao(private val jdbcTemplate: JdbcTemplate) {

    fun findAllDeliveryOrders(): List<Map<String, Any>> {
        val sql = """
            SELECT d.*, p.name as party_name 
            FROM delivery_order d
            LEFT JOIN party p ON d.party_id = p.id
            ORDER BY d.created_at DESC
        """.trimIndent()

        return jdbcTemplate.queryForList(sql)
    }
    fun createDeliveryOrder(deliveryOrder: DeliveryOrder): Boolean {
        val sql = """
            INSERT INTO delivery_order (
                id, contract_id, party_id, date_of_contract, status,
                grand_total_quantity, grand_total_pending_quantity,
                grand_total_in_progress_quantity, grand_total_delivered_quantity,
                created_at, updated_at
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return try {
            jdbcTemplate.update(
                sql,
                deliveryOrder.id,
                deliveryOrder.contractId,
                deliveryOrder.partyId,
                deliveryOrder.dateOfContract,
                deliveryOrder.status,
                deliveryOrder.grandTotalQuantity,
                deliveryOrder.grandTotalPendingQuantity,
                deliveryOrder.grandTotalInProgressQuantity,
                deliveryOrder.grandTotalDeliveredQuantity,
                deliveryOrder.createdAt,
                deliveryOrder.updatedAt
            ) > 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to create delivery order: ${e.message}")
        }
    }

    fun createDeliveryOrderItem(item: DeliveryOrderItem): Boolean {
        val sql = """
            INSERT INTO delivery_order_item (
                id, delivery_order_id, district, taluka, location_id,
                material_id, quantity, pending_quantity, delivered_quantity,
                in_progress_quantity, rate, unit, due_date, status
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        return try {
            jdbcTemplate.update(
                sql,
                item.id,
                item.deliveryOrderId,
                item.district,
                item.taluka,
                item.locationId,
                item.materialId,
                item.quantity,
                item.pendingQuantity,
                item.deliveredQuantity,
                item.inProgressQuantity,
                item.rate,
                item.unit,
                item.dueDate,
                item.status
            ) > 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to create delivery order item: ${e.message}")
        }
    }

    fun findDeliveryOrderById(id: String): DeliveryOrder? {
        val sql = """
            SELECT * FROM delivery_order WHERE id = ?
        """.trimIndent()

        return try {
            jdbcTemplate.queryForObject(sql, { rs, _ ->
                DeliveryOrder(
                    id = rs.getString("id"),
                    contractId = rs.getString("contract_id"),
                    partyId = rs.getString("party_id"),
                    dateOfContract = rs.getLong("date_of_contract"),
                    status = rs.getString("status"),
                    grandTotalQuantity = rs.getInt("grand_total_quantity"),
                    grandTotalPendingQuantity = rs.getInt("grand_total_pending_quantity"),
                    grandTotalInProgressQuantity = rs.getInt("grand_total_in_progress_quantity"),
                    grandTotalDeliveredQuantity = rs.getInt("grand_total_delivered_quantity"),
                    createdAt = rs.getLong("created_at"),
                    updatedAt = rs.getLong("updated_at")
                )
            }, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun findDeliveryOrderItemsByOrderId(orderId: String): List<DeliveryOrderItem> {
        val sql = """
            SELECT * FROM delivery_order_item WHERE delivery_order_id = ?
        """.trimIndent()

        return jdbcTemplate.query(sql, { rs, _ ->
            DeliveryOrderItem(
                id = rs.getString("id"),
                deliveryOrderId = rs.getString("delivery_order_id"),
                district = rs.getString("district"),
                taluka = rs.getString("taluka"),
                locationId = rs.getString("location_id"),
                materialId = rs.getString("material_id"),
                quantity = rs.getInt("quantity"),
                pendingQuantity = rs.getInt("pending_quantity"),
                deliveredQuantity = rs.getInt("delivered_quantity"),
                inProgressQuantity = rs.getInt("in_progress_quantity"),
                rate = rs.getDouble("rate"),
                unit = rs.getString("unit"),
                dueDate = rs.getLong("due_date"),
                status = rs.getString("status")
            )
        }, orderId)
    }

    fun updateDeliveryOrder(deliveryOrder: DeliveryOrder): Boolean {
        val sql = """
        UPDATE delivery_order SET
            grand_total_quantity = ?,
            grand_total_pending_quantity = ?,
            grand_total_in_progress_quantity = ?,
            grand_total_delivered_quantity = ?,
            updated_at = ?
        WHERE id = ?
    """.trimIndent()

        return try {
            jdbcTemplate.update(
                sql,
                deliveryOrder.grandTotalQuantity,
                deliveryOrder.grandTotalPendingQuantity,
                deliveryOrder.grandTotalInProgressQuantity,
                deliveryOrder.grandTotalDeliveredQuantity,
                deliveryOrder.updatedAt,
                deliveryOrder.id
            ) > 0
        } catch (e: Exception) {
            throw IllegalStateException("Failed to update delivery order: ${e.message}")
        }
    }
}