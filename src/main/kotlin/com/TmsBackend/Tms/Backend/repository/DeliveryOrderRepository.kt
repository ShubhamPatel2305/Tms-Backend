package com.TmsBackend.Tms.Backend.repository

import com.TmsBackend.Tms.Backend.dao.DeliveryOrderDao
import org.springframework.stereotype.Repository

@Repository
class DeliveryOrderRepository(private val doDao: DeliveryOrderDao) {

    fun createDo(
        id: String,
        doId: String,
        partyId: String,
        unitId: String,
        total: Int,
        pending: Int,
        ongoing: Int,
        status: String,
        createdAt: Long,
        updatedAt: Long
    ): Boolean = doDao.createDo(id, doId, partyId, unitId, total, pending, ongoing, status, createdAt, updatedAt)

    fun createDoItem(
        id: String,
        doId: String,
        state: String,
        district: String,
        taluka: String,
        city: String,
        locationId: String,
        materialId: String,
        quantity: Int,
        unitId: String,
        deadline: Long,
        pending: Int,
        ongoing: Int,
        status: String,
        createdAt: Long,
        updatedAt: Long
    ): Boolean = doDao.createDoItem(id, doId, state, district, taluka, city, locationId, materialId, quantity, unitId, deadline, pending, ongoing, status, createdAt, updatedAt)

    fun findDoById(id: String): Map<String, Any>? = doDao.findDoById(id)

    fun updateDo(
        id: String,
        doId: String,
        partyId: String,
        unitId: String,
        updatedAt: Long
    ): Boolean = doDao.updateDo(id, doId, partyId, unitId, updatedAt)

    fun updateDoTotals(
        id: String,
        total: Int,
        pending: Int,
        ongoing: Int,
        updatedAt: Long
    ): Boolean = doDao.updateDoTotals(id, total, pending, ongoing, updatedAt)

    fun deleteDoItems(doId: String): Boolean = doDao.deleteDoItems(doId)
}