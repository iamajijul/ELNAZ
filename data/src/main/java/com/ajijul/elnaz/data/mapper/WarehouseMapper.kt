package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.WarehouseEntity
import com.ajijul.elnaz.domain.model.WarehouseModel
import java.util.UUID

/**
 * Maps Database Entity -> Domain Model (For UI)
 */
fun WarehouseEntity.toDomain() = WarehouseModel(
    id = id,
    code = code,
    name = name,
    location = location,
    contactNumber = contactNumber,
    managerName = managerName,
    status = status,
    createdAt = createdAt
)

/**
 * Maps Domain Model -> Database Entity (For Saving)
 * Requires [shopId] to ensure multi-tenant safety.
 */
fun WarehouseModel.toEntity(shopId: String) = WarehouseEntity(
    id = id.ifBlank { UUID.randomUUID().toString() },
    shopId = shopId,
    code = code,
    name = name,
    location = location,
    contactNumber = contactNumber,
    managerName = managerName,
    status = status,

    // SaaS/Sync Defaults
    createdAt = createdAt,
    lastUpdated = System.currentTimeMillis()
    // syncState and isDeleted will use Entity defaults
)