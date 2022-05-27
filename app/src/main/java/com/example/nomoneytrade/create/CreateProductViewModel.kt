package com.example.nomoneytrade.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.dto.PostTag
import com.example.nomoneytrade.api.dto.ProductDto
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.mvi.event.CreateProductEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProductViewModel @Inject constructor(private val api: Api) : ViewModel() {

    val event = MutableStateFlow<CreateProductEvent>(CreateProductEvent.Loading)

    init {

    }

    fun clickCreate(product: Product) {
        this.viewModelScope.launch {
            createProduct(product)
        }
    }

    private suspend fun createProduct(product: Product) {
        event.value = CreateProductEvent.Loading

        val set = mutableSetOf<PostTag>()
        val mappedList = product.tags.map {
            PostTag(-1, it) //TODO NEED HELP
        }.forEach {
            set.add(it)
        }

        val response = api.newProduct(
            ProductDto(
                id = product.id,
                user_id = product.userId,
                title = product.title,
                description = product.description,
                tags = set
            )
        )

        if (response.isSuccessful) {
            event.value = CreateProductEvent.Success
        } else {
            event.value = CreateProductEvent.Error
        }
    }
}