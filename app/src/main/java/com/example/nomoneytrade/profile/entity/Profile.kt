package com.example.nomoneytrade.profile.entity

data class Profile(
    val id: Int,
    val username: String,
    val fio: String,
    val email: String,
    val iconUrl: String,
    val city: String,
    val address: String,
    val phoneNumber: String,
)