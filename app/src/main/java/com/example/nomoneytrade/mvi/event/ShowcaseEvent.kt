package com.example.nomoneytrade.mvi.event

import com.example.nomoneytrade.showcase.ProductPreview


sealed class ShowcaseEvent {
    object Loading: ShowcaseEvent()
    class Success(val products: List<ProductPreview>): ShowcaseEvent()
    object Error: ShowcaseEvent()
}