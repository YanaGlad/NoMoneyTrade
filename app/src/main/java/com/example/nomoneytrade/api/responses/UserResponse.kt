package com.example.nomoneytrade.api.responses

import kotlinx.serialization.Serializable

@Serializable
class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val imagePath: String,
    val phoneNumber: String,
)