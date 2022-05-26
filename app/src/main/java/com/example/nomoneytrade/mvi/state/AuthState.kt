package com.example.nomoneytrade.mvi.state

import com.example.nomoneytrade.api.dto.UserDto

data class AuthState(
    val userDto: UserDto
) {
    constructor() : this(UserDto(0, "", "", "", true))
}