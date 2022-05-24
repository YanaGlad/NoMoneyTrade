package com.example.nomoneytrade.showcase.entity

data class Product(
    val id: Int,
    val sellerId: Int,
    val title: String,
    val imageUrl: String,
    val tags: List<String>,
    val isFeatured: Boolean,
)