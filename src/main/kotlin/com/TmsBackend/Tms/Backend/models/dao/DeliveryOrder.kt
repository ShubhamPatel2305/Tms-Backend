package com.TmsBackend.Tms.Backend.models.dao

data class DeliveryOrder(
    val id: String,
    val contractId: String?,
    val partyId: String?,
    val dateOfContract: Long?,
    val status: String,
    val grandTotalQuantity: Int,
    val grandTotalPendingQuantity: Int,
    val grandTotalInProgressQuantity: Int,
    val grandTotalDeliveredQuantity: Int,
    val createdAt: Long?,
    val updatedAt: Long?
)

data class DeliveryOrderItem(
    val id: String,
    val deliveryOrderId: String,
    val district: String?,
    val taluka: String?,
    val locationId: String?,
    val materialId: String?,
    val quantity: Int,
    val pendingQuantity: Int,
    val deliveredQuantity: Int,
    val inProgressQuantity: Int,
    val rate: Double?,
    val unit: String?,
    val dueDate: Long?,
    val status: String
)