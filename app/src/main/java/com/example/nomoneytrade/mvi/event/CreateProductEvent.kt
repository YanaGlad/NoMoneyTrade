package com.example.nomoneytrade.mvi.event

sealed class CreateProductEvent {
    object Success: CreateProductEvent()
    object Error: CreateProductEvent()
    object Loading: CreateProductEvent()
}