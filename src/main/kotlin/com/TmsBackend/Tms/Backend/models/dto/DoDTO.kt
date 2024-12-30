package com.TmsBackend.Tms.Backend.models.dto


data class DeliveryOrderItemDTO(
    val id: String?,
    val deliveryOrderId: String?,
    val district: String?,
    val taluka: String?,
    val locationId: String?,
    val materialId: String?,
    val quantity: Int,
    val pendingQuantity: Int = 0,
    val deliveredQuantity: Int = 0,
    val inProgressQuantity: Int = 0,
    val rate: Double?,
    val unit: String?,
    val dueDate: Long?,
    val status: String
)

data class DeliveryOrderSectionDTO(
    val district: String?,
    val totalQuantity: Int = 0,
    val totalPendingQuantity: Int = 0,
    val totalInProgressQuantity: Int = 0,
    val totalDeliveredQuantity: Int = 0,
    val status: String,
    val deliveryOrderItems: List<DeliveryOrderItemDTO>?
)

data class DeliveryOrderDTO(
    val id: String?,
    val contractId: String?,
    val partyId: String?,
    val dateOfContract: Long?,
    val status: String,
    val grandTotalQuantity: Int = 0,
    val grandTotalPendingQuantity: Int = 0,
    val grandTotalInProgressQuantity: Int = 0,
    val grandTotalDeliveredQuantity: Int = 0,
    val createdAt: Long? = null,
    val updatedAt: Long? = null,
    val deliveryOrderSections: List<DeliveryOrderSectionDTO>?
)

data class DeliveryOrderListDTO(
    val id: String,
    val contractId: String?,
    val partyName: String,  // From party table
    val dateOfContract: Long?,
    val grandTotalQuantity: Int,
    val grandTotalPendingQuantity: Int,
    val grandTotalInProgressQuantity: Int,
    val grandTotalDeliveredQuantity: Int,
    val status: String
)