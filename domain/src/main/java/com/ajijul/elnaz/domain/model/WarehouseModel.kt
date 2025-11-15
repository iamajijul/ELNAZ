package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.WarehouseStatus

data class WarehouseModel(
    val id: Long = 0,
    val code: String,
    val name: String,
    val location: String,
    val contactNumber: String?,
    val managerName: String?,
    val status: WarehouseStatus = WarehouseStatus.ACTIVE,
    val createdAt: Long
)
