package com.TmsBackend.Tms.Backend.models.dto


data class DoRequest(
    val id: String?, // If present, update the existing Do
    val do_id: String,
    val party_id: String,
    val unit_id: String,
    val dateOfContract: Long,
    val do_items: List<DoItemRequest>? // Optional list of Do items
)

data class DoItemRequest(
    val district: String,
    val state: String,
    val taluka: String,
    val city: String,
    val location_id: String,
    val material_id: String,
    val quantity: Int,
    val unit_id: String,
    val deadline: Long,
//    val pending: Int,
//    val ongoing: Int,
//    val status: String
)

data class DoResponse(
    val id: String,
    val do_id: String,
    val party_id: String,
    val unit_id: String,
    val do_items: List<DoItemResponse>
)

data class DoItemResponse(
    val id: String,
    val district: String,
    val state: String,
    val taluka: String,
    val city: String,
    val location_id: String,
    val material_id: String,
    val quantity: Int,
    val unit_id: String,
    val deadline: Long,
    val pending: Int,
    val ongoing: Int,
    val status: String
)

