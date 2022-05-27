package com.example.nomoneytrade.api.requests

import kotlinx.serialization.Serializable

@Serializable
class ProductRequest(
    val title: String,
    val user_id: Long,
    val description: String,
    val tags: List<String>,
    val tagsExchange: List<String>,
    val city: String,
    val time: String,
)