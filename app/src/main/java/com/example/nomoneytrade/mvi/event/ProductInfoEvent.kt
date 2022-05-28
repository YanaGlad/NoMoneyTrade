package com.example.nomoneytrade.mvi.event

import com.example.nomoneytrade.entity.User

sealed class ProductInfoEvent {
    class Success(val user: User) : ProductInfoEvent()
    object Loading: ProductInfoEvent()
    object Error: ProductInfoEvent()
}