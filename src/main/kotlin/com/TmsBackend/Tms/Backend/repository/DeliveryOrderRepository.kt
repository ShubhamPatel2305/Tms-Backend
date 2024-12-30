package com.TmsBackend.Tms.Backend.repository

import com.TmsBackend.Tms.Backend.dao.DeliveryOrderDao
import com.TmsBackend.Tms.Backend.models.dao.DeliveryOrder
import com.TmsBackend.Tms.Backend.models.dao.DeliveryOrderItem
import com.TmsBackend.Tms.Backend.models.dto.DeliveryOrderListDTO
import org.springframework.stereotype.Repository

@Repository
class DeliveryOrderRepository(private val deliveryOrderDao: DeliveryOrderDao) {

    fun createDeliveryOrder(deliveryOrder: DeliveryOrder): Boolean =
        deliveryOrderDao.createDeliveryOrder(deliveryOrder)

    fun createDeliveryOrderItem(item: DeliveryOrderItem): Boolean =
        deliveryOrderDao.createDeliveryOrderItem(item)

    fun findDeliveryOrderById(id: String): DeliveryOrder? =
        deliveryOrderDao.findDeliveryOrderById(id)

    fun findDeliveryOrderItemsByOrderId(orderId: String): List<DeliveryOrderItem> =
        deliveryOrderDao.findDeliveryOrderItemsByOrderId(orderId)

    fun findAllDeliveryOrders(): List<DeliveryOrderListDTO> {
        return deliveryOrderDao.findAllDeliveryOrders().map { result ->
            DeliveryOrderListDTO(
                id = result["id"] as String,
                contractId = result["contract_id"] as String?,
                partyName = result["party_name"] as String,
                dateOfContract = (result["date_of_contract"] as? Number)?.toLong(),
                grandTotalQuantity = (result["grand_total_quantity"] as Number).toInt(),
                grandTotalPendingQuantity = (result["grand_total_pending_quantity"] as Number).toInt(),
                grandTotalInProgressQuantity = (result["grand_total_in_progress_quantity"] as Number).toInt(),
                grandTotalDeliveredQuantity = (result["grand_total_delivered_quantity"] as Number).toInt(),
                status = result["status"] as String
            )
        }
    }

    fun updateDeliveryOrder(deliveryOrder: DeliveryOrder): Boolean =
        deliveryOrderDao.updateDeliveryOrder(deliveryOrder)
}