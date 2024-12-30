package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.DeliveryOrderDTO
import com.TmsBackend.Tms.Backend.models.dto.DeliveryOrderListDTO
import com.TmsBackend.Tms.Backend.service.DeliveryOrderService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/delivery-orders")
@CrossOrigin
class DeliveryOrderController(private val deliveryOrderService: DeliveryOrderService) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/create")
    fun createDeliveryOrder(@RequestBody request: DeliveryOrderDTO): ResponseEntity<Any> {
        return try {
            logger.info("Received request to create delivery order: $request")
            val result = deliveryOrderService.createDeliveryOrder(request)
            ResponseEntity.ok(result)
        } catch (e: IllegalArgumentException) {
            logger.error("Bad request error: ${e.message}", e)
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        } catch (e: Exception) {
            logger.error("Internal server error while creating delivery order", e)
            ResponseEntity.internalServerError().body(mapOf(
                "error" to "An unexpected error occurred",
                "details" to e.message
            ))
        }
    }

    @GetMapping("/get/{id}")
    fun getDeliveryOrderById(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            logger.info("Received request to fetch delivery order with ID: $id")
            val deliveryOrder = deliveryOrderService.getDeliveryOrderById(id)
            ResponseEntity.ok(deliveryOrder)
        } catch (e: IllegalArgumentException) {
            logger.error("Bad request error: ${e.message}", e)
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        } catch (e: Exception) {
            logger.error("Internal server error while fetching delivery order", e)
            ResponseEntity.internalServerError().body(mapOf(
                "error" to "An unexpected error occurred",
                "details" to e.message
            ))
        }
    }

    @GetMapping("/list")
    fun getAllDeliveryOrders(): ResponseEntity<List<DeliveryOrderListDTO>> {
        return try {
            val orders = deliveryOrderService.getAllDeliveryOrders()
            ResponseEntity.ok(orders)
        } catch (e: Exception) {
            logger.error("Error fetching delivery orders", e)
            ResponseEntity.internalServerError().build()
        }
    }

    @PostMapping("/{id}/add-items")
    fun addItemsToDeliveryOrder(
        @PathVariable id: String,
        @RequestBody request: DeliveryOrderDTO
    ): ResponseEntity<Any> {
        return try {
            println("hii")
            logger.info("Received request to add items to delivery order $id: $request")
            val result = deliveryOrderService.addItemsToDeliveryOrder(id, request)
            println(result)
            ResponseEntity.ok(result)
        } catch (e: IllegalArgumentException) {
            logger.error("Bad request error: ${e.message}", e)
            println(e.message)
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        } catch (e: Exception) {
            logger.error("Internal server error while adding items to delivery order", e)
            println(e.message)
            ResponseEntity.internalServerError().body(mapOf(
                "error" to "An unexpected error occurred",
                "details" to e.message
            ))
        }
    }
}