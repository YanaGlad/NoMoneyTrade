package com.example.nomoneytrade.api.responses

import kotlinx.serialization.Serializable

@Serializable
class UserResponse(
    val username: String,
    val email: String,
    val password: String,
    val imagePath: String,
    val city: String,
    val address: String,
    val phoneNumber: String,
)