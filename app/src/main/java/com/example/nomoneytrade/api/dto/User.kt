package com.example.nomoneytrade.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("email") val email: String,
    @SerialName("enabled") val isEnabled: Boolean,
    @SerialName("imagePath") val imagePath: String
)