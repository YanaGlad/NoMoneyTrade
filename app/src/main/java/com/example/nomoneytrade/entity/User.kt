package com.example.nomoneytrade.profile.entity

interface UserI

object NoImpl: UserI

data class User(
    val id: Long,
    val username: String,
    val fio: String,
    val email: String,
    val iconUrl: String,
    val city: String,
    val address: String,
    val phoneNumber: String,
): UserI