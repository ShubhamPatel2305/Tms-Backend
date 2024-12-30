package com.TmsBackend.Tms.Backend.service

import com.TmsBackend.Tms.Backend.models.dao.DeliveryOrder
import com.TmsBackend.Tms.Backend.models.dao.DeliveryOrderItem
import com.TmsBackend.Tms.Backend.models.dto.*
import com.TmsBackend.Tms.Backend.repository.DeliveryOrderRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeliveryOrderService(private val deliveryOrderRepository: DeliveryOrderRepository) {

    fun createDeliveryOrder(request: DeliveryOrderDTO): Map<String, Any> {
        val currentTime = System.currentTimeMillis()
        val orderId = UUID.randomUUID().toString()

        // Calculate total quantities from items
        val allItems = request.deliveryOrderSections?.flatMap { it.deliveryOrderItems ?: emptyList() } ?: emptyList()
        val grandTotalQuantity = allItems.sumOf { it.quantity }

        // Create delivery order with calculated quantities
        val deliveryOrder = DeliveryOrder(
            id = orderId,
            contractId = request.contractId,
            partyId = request.partyId,
            dateOfContract = request.dateOfContract,
            status = request.status,
            grandTotalQuantity = grandTotalQuantity,
            grandTotalPendingQuantity = grandTotalQuantity, // Initially all quantity is pending
            grandTotalInProgressQuantity = 0,
            grandTotalDeliveredQuantity = 0,
            createdAt = currentTime,
            updatedAt = currentTime
        )

        deliveryOrderRepository.createDeliveryOrder(deliveryOrder)

        // Create delivery order items
        request.deliveryOrderSections?.forEach { section ->
            section.deliveryOrderItems?.forEach { itemDto ->
                val deliveryOrderItem = DeliveryOrderItem(
                    id = UUID.randomUUID().toString(),
                    deliveryOrderId = orderId,
                    district = itemDto.district,
                    taluka = itemDto.taluka,
                    locationId = itemDto.locationId,
                    materialId = itemDto.materialId,
                    quantity = itemDto.quantity,
                    pendingQuantity = itemDto.quantity, // Initially all quantity is pending
                    deliveredQuantity = 0,
                    inProgressQuantity = 0,
                    rate = itemDto.rate,
                    unit = itemDto.unit ?: "",
                    dueDate = itemDto.dueDate ?: 0,
                    status = itemDto.status
                )
                deliveryOrderRepository.createDeliveryOrderItem(deliveryOrderItem)
            }
        }

        return mapOf(
            "message" to "Delivery order created successfully",
            "id" to orderId
        )
    }

    fun getDeliveryOrderById(id: String): DeliveryOrderDTO {
        val deliveryOrder = deliveryOrderRepository.findDeliveryOrderById(id)
            ?: throw IllegalArgumentException("Delivery order not found with ID: $id")

        val items = deliveryOrderRepository.findDeliveryOrderItemsByOrderId(id)

        // Group items by district and calculate section totals
        val sections = items.groupBy { it.district }
            .map { (district, districtItems) ->
                DeliveryOrderSectionDTO(
                    district = district,
                    totalQuantity = districtItems.sumOf { it.quantity },
                    totalPendingQuantity = districtItems.sumOf { it.pendingQuantity },
                    totalInProgressQuantity = districtItems.sumOf { it.inProgressQuantity },
                    totalDeliveredQuantity = districtItems.sumOf { it.deliveredQuantity },
                    status = calculateSectionStatus(districtItems),
                    deliveryOrderItems = districtItems.map { item ->
                        DeliveryOrderItemDTO(
                            id = item.id,
                            deliveryOrderId = item.deliveryOrderId,
                            district = item.district,
                            taluka = item.taluka,
                            locationId = item.locationId,
                            materialId = item.materialId,
                            quantity = item.quantity,
                            pendingQuantity = item.pendingQuantity,
                            deliveredQuantity = item.deliveredQuantity,
                            inProgressQuantity = item.inProgressQuantity,
                            rate = item.rate,
                            unit = item.unit,
                            dueDate = item.dueDate,
                            status = item.status
                        )
                    }
                )
            }

        return DeliveryOrderDTO(
            id = deliveryOrder.id,
            contractId = deliveryOrder.contractId,
            partyId = deliveryOrder.partyId,
            dateOfContract = deliveryOrder.dateOfContract,
            status = deliveryOrder.status,
            grandTotalQuantity = deliveryOrder.grandTotalQuantity,
            grandTotalPendingQuantity = deliveryOrder.grandTotalPendingQuantity,
            grandTotalInProgressQuantity = deliveryOrder.grandTotalInProgressQuantity,
            grandTotalDeliveredQuantity = deliveryOrder.grandTotalDeliveredQuantity,
            createdAt = deliveryOrder.createdAt,
            updatedAt = deliveryOrder.updatedAt,
            deliveryOrderSections = sections
        )
    }

    private fun calculateSectionStatus(items: List<DeliveryOrderItem>): String {
        return when {
            items.all { it.status == "COMPLETED" } -> "COMPLETED"
            items.all { it.status == "pending" } -> "pending"
            else -> "IN_PROGRESS"
        }
    }

    // getAllDeliveryOrders remains the same
    fun getAllDeliveryOrders(): List<DeliveryOrderListDTO> {
        return deliveryOrderRepository.findAllDeliveryOrders()
    }

    fun addItemsToDeliveryOrder(orderId: String, request: DeliveryOrderDTO): Map<String, Any> {
        // Verify delivery order exists
        val existingOrder = deliveryOrderRepository.findDeliveryOrderById(orderId)
            ?: throw IllegalArgumentException("Delivery order not found with ID: $orderId")

        // Calculate additional quantities from new items
        val newItems = request.deliveryOrderSections?.flatMap { it.deliveryOrderItems ?: emptyList() } ?: emptyList()
        val additionalQuantity = newItems.sumOf { it.quantity }

        // Update delivery order totals
        val updatedOrder = existingOrder.copy(
            grandTotalQuantity = existingOrder.grandTotalQuantity + additionalQuantity,
            grandTotalPendingQuantity = existingOrder.grandTotalPendingQuantity + additionalQuantity,
            updatedAt = System.currentTimeMillis()
        )

        deliveryOrderRepository.updateDeliveryOrder(updatedOrder)

        // Create new delivery order items
        request.deliveryOrderSections?.forEach { section ->
            section.deliveryOrderItems?.forEach { itemDto ->
                val deliveryOrderItem = DeliveryOrderItem(
                    id = UUID.randomUUID().toString(),
                    deliveryOrderId = orderId,
                    district = itemDto.district,
                    taluka = itemDto.taluka,
                    locationId = itemDto.locationId,
                    materialId = itemDto.materialId,
                    quantity = itemDto.quantity,
                    pendingQuantity = itemDto.quantity, // Initially all quantity is pending
                    deliveredQuantity = 0,
                    inProgressQuantity = 0,
                    rate = itemDto.rate,
                    unit = itemDto.unit ?: "",
                    dueDate = itemDto.dueDate ?: 0,
                    status = existingOrder.status  // Use the same status as parent order
                )
                deliveryOrderRepository.createDeliveryOrderItem(deliveryOrderItem)
            }
        }

        return mapOf(
            "message" to "Items added successfully to delivery order",
            "id" to orderId
        )
    }
}