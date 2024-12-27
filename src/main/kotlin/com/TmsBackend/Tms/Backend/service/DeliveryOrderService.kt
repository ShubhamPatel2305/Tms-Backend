package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dto.DoRequest
import com.TmsBackend.Tms.Backend.repository.DeliveryOrderRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeliveryOrderService(private val deliveryOrderRepository: DeliveryOrderRepository) {

    fun createOrUpdateDo(request: DoRequest): Map<String, Any> {
        return if (request.id != null) {
            updateDo(request)
        } else {
            createNewDo(request)
        }
    }

    private fun createNewDo(request: DoRequest): Map<String, Any> {
        val doId = UUID.randomUUID().toString()
        val currentTime = System.currentTimeMillis()

        // Calculate totals based on DO items if present
        val totalQuantity = request.do_items?.sumOf { it.quantity } ?: 0

        val doResult = deliveryOrderRepository.createDo(
            id = doId,
            doId = request.do_id,
            partyId = request.party_id,
            unitId = request.unit_id,
            total = totalQuantity,
            pending = totalQuantity,
            ongoing = 0,
            status = "CREATED",
            createdAt = currentTime,
            updatedAt = currentTime
        )

        // Create DO items if present
        request.do_items?.let { items ->
            items.forEach { item ->
                deliveryOrderRepository.createDoItem(
                    id = UUID.randomUUID().toString(),
                    doId = doId,
                    state = item.state,
                    district = item.district,
                    taluka = item.taluka,
                    city = item.city,
                    locationId = item.location_id,
                    materialId = item.material_id,
                    quantity = item.quantity,
                    unitId = item.unit_id,
                    deadline = item.deadline,
                    pending = item.quantity,
                    ongoing = 0,
                    status = "CREATED",
                    createdAt = currentTime,
                    updatedAt = currentTime
                )
            }
        }

        return mapOf(
            "message" to "DO created successfully",
            "do_id" to doId
        )
    }

    private fun updateDo(request: DoRequest): Map<String, Any> {
        val currentTime = System.currentTimeMillis()

        // Verify DO exists
        val existingDo = deliveryOrderRepository.findDoById(request.id!!)
            ?: throw IllegalArgumentException("DO not found with ID: ${request.id}")

        // Update DO
        deliveryOrderRepository.updateDo(
            id = request.id,
            doId = request.do_id,
            partyId = request.party_id,
            unitId = request.unit_id,
            updatedAt = currentTime
        )

        // Handle DO items if present
        request.do_items?.let { items ->
            val totalQuantity = items.sumOf { it.quantity }

            // Update DO totals
            deliveryOrderRepository.updateDoTotals(
                id = request.id,
                total = totalQuantity,
                pending = totalQuantity,
                ongoing = 0,
                updatedAt = currentTime
            )

            // Delete existing items and create new ones
            deliveryOrderRepository.deleteDoItems(request.id)

            items.forEach { item ->
                deliveryOrderRepository.createDoItem(
                    id = UUID.randomUUID().toString(),
                    doId = request.id,
                    state = item.state,
                    district = item.district,
                    taluka = item.taluka,
                    city = item.city,
                    locationId = item.location_id,
                    materialId = item.material_id,
                    quantity = item.quantity,
                    unitId = item.unit_id,
                    deadline = item.deadline,
                    pending = item.quantity,
                    ongoing = 0,
                    status = "CREATED",
                    createdAt = currentTime,
                    updatedAt = currentTime
                )
            }
        }

        return mapOf(
            "message" to "DO updated successfully",
            "do_id" to request.id
        )
    }
}