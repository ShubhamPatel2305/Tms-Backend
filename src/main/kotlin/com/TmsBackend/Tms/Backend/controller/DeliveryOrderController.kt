package com.TmsBackend.Tms.Backend.controller

import com.TmsBackend.Tms.Backend.models.dto.DoRequest
import com.TmsBackend.Tms.Backend.service.DeliveryOrderService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/do")
class DeliveryOrderController(private val deliveryOrderService: DeliveryOrderService) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun createOrUpdateDo(@RequestBody request: DoRequest): ResponseEntity<Any> {
        return try {
            logger.info("Received request to create/update DO: $request")
            val result = deliveryOrderService.createOrUpdateDo(request)
            ResponseEntity.ok(result)
        } catch (e: IllegalArgumentException) {
            logger.error("Bad request error: ${e.message}", e)
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        } catch (e: Exception) {
            logger.error("Internal server error while processing DO request", e)
            ResponseEntity.internalServerError().body(mapOf(
                "error" to "An unexpected error occurred",
                "details" to e.message
            ))
        }
    }
}