package com.example.nomoneytrade.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProductDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("user_id") val user_id: Long,
    @SerialName("description") val description: String,
    @SerialName("tags") val tags: List<String>,
)