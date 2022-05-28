package com.example.nomoneytrade.entity

interface UserI

object NoImpl: UserI

data class User(
    val id: Long,
    val username: String,
    val fio: String,
    val email: String,
    val iconUrl: String,
    val phoneNumber: String,
): UserI