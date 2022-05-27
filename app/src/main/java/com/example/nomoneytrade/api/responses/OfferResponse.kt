package com.example.nomoneytrade.api.responses

import com.example.nomoneytrade.profile.entity.User
import kotlinx.serialization.Serializable

@Serializable
data class OfferResponse(
    val user: UserResponse,
    val seller: UserResponse,
    val userListing: ProductResponse,
    val theirListing: ProductResponse,
    val place: String,
    val time: String,
)