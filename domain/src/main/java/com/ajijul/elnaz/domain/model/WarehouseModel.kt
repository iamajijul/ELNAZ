package com.ajijul.elnaz.domain.model

data class WarehouseModel(
    val id: String,
    val name: String,
    val managerName: String,
    val totalEmployees: Int,
    val address: String,
    val phone: String
)
