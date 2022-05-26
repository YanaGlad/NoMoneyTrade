package com.example.nomoneytrade.mvi.event

sealed class BottomNavEvent {
    object Profile: BottomNavEvent()
    object Showcase : BottomNavEvent()
    object Offers: BottomNavEvent()
    object CreateProduct: BottomNavEvent()
}