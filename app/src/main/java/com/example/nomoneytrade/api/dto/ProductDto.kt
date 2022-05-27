package com.example.nomoneytrade.api.dto

class ProductDto(
    val id: Long,
    val title: String,
    val user_id: Long,
    val description: String,
    val tags: List<String>,
)