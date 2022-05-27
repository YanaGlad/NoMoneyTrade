package com.example.nomoneytrade.showcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.ShowcaseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowcaseViewModel @Inject constructor(val api: Api) : ViewModel() {

    val event = MutableStateFlow<ShowcaseEvent>(ShowcaseEvent.Loading)

    init {
        event.value = ShowcaseEvent.Loading

        this.viewModelScope.launch {
            loadProducts()
        }
    }


    private suspend fun loadProducts() {
        val response = api.getAllProducts()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            event.value = ShowcaseEvent.Success(body.posts.map {
                Product(
                    id = it.id,
                    userId = it.user_id,
                    title = it.title,
                    imageUrl = "", //TODO почему у нас в дто нет картинки?
                    description = it.description,
                    favourites = false,
                    tags = it.tags
                )
            })
        } else {
            event.value = ShowcaseEvent.Error
        }
    }
}