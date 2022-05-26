package com.example.nomoneytrade.mvi.state

import com.example.nomoneytrade.api.dto.UserDto

data class AuthState(
    val user: UserDto
) {
    constructor() : this(UserDto(0, "", "", "", true, ""))
}