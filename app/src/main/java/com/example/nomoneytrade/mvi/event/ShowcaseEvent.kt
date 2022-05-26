package com.example.nomoneytrade.mvi.event

import com.example.nomoneytrade.entity.Product


sealed class ShowcaseEvent {
    object Loading: ShowcaseEvent()
    class Success(val products: List<Product>): ShowcaseEvent()
    object Error: ShowcaseEvent()
}