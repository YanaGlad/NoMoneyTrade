package com.example.nomoneytrade.showcase

data class ProductPreview(
    val id: Int,
    val userId: Int,
    val title: String,
    val imageUrl: String,
    val description: String, //.substring(0,100) ?
    val favourites: Boolean, //favs
    val tags: List<String>
)