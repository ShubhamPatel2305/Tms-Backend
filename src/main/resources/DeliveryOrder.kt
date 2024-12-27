package app.tmsbackend.model

import java.util.UUID

data class Party(
    val id: String?,
    val companyName: String,
    val pointOfContact: String?,
    val contactNumber: String,
    val email: String?,
    val addressLine1: String?,
    val addressLine2: String?,
    val state: String?,
    val district: String?,
    val taluka: String?,
    val city: String?,
    val pinCode: String?,
)

data class Material(
    val id: String = UUID.randomUUID().toString(),
    val materialName: String,
)

data class Location(
    val id: String?,
    val companyName: String,
    val pointOfContact: String?,
    val contactNumber: String,
    val email: String?,
    val addressLine1: String?,
    val addressLine2: String?,
    val state: String?,
    val district: String,
    val taluka: String,
    val city: String?,
    val pinCode: String?,
)

data class DeliveryOrder (
    val id: String? = UUID.randomUUID().toString(),
    val do_id:String,
    val party_id: String,
    val dateOfContract: Long, //this is different from created_at
    val deliveryOrderSections: List<DeliveryOrderSection> = emptyList(),
    val unit_id:String,
    val grandTotalQuantity: Double = 0.0,
    val grandPendingQuantity: Double = 0.0,
    val grandTotalInProgressQuantity: Double = 0.0,
    val grandDeliveredQuantity: Double = 0.0,
    //val associatedDeliveryChallan: List<DeliveryChallan> = emptyList(),
    val status : String,
    val created_at: Long,
    val updated_at: Long
)

data class DeliveryOrderSection(
    val id: String = UUID.randomUUID().toString(),
    val district: String,
    val deliveryOrderItems: List<DeliveryOrderItem> = emptyList(),

    val totalQuantity: Double = 0.0,
    val totalPendingQuantity: Double = 0.0,
    val totalInProgressQuantity: Double = 0.0,
    val totalDeliveredQuantity: Double = 0.0,

    val status: String = "PENDING",
)

data class DeliveryOrderItem(
    val id: String = UUID.randomUUID().toString(),
    val district: String,
    val taluka: String,
    val state: String,
    val city: String,
    val location_id: String,

    val material_id: String,
    val quantity: Double = 0.0,
    val pendingQuantity: Double = 0.0,
    val deliveredQuantity: Double = 0.0,
    val inProgressQuantity: Double = 0.0,
    val rate: Double = 0.0,
    val associatedDeliveryChallan: List<DeliveryChallan> = emptyList(),
    val status: String = "PENDING",
)


data class DeliveryChallan(
    val id: String = UUID.randomUUID().toString(),
)