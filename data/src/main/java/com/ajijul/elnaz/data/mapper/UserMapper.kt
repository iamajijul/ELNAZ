package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.dto.UserDTO
import com.ajijul.elnaz.domain.auth.UserModel

fun UserModel.toDto(): UserDTO {
    return UserDTO(
        name = name,
        email = email,
        mobile = mobile,
        address = address,
        role = role
    )
}

fun UserDTO.toModel(): UserModel {
    return UserModel(
        uid = uid,
        email = email,
        name = name,
        mobile = mobile,
        address = address,
        role = role,
        createdAt = createdAt?.toDate()?.time ?: 0L
    )
}
