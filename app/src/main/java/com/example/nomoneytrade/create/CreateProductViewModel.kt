package com.example.nomoneytrade.create


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.dto.ProductDto
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.CreateProductEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProductViewModel @Inject constructor(private val api: Api) : ViewModel() {

    val event = MutableStateFlow<CreateProductEvent>(CreateProductEvent.Loading)

    fun clickCreate(product: Product) {
        this.viewModelScope.launch {
            createProduct(product)
        }
    }

    private suspend fun createProduct(product: Product) {
        event.value = CreateProductEvent.Loading

        val response = api.newProduct(
            ProductDto(
                id = product.id,
                user_id = product.userId,
                title = product.title,
                description = product.description,
                tags = product.tags,
            )
        )

        if (response.isSuccessful) {
            event.value = CreateProductEvent.Success
        } else {
            event.value = CreateProductEvent.Error
        }
    }
}