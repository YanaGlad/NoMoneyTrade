package com.example.nomoneytrade.showcase

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.dto.TagDto
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.ShowcaseEvent
import com.example.nomoneytrade.stubList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowcaseViewModel @Inject constructor(val api: Api) : ViewModel() {

    val event = MutableStateFlow<ShowcaseEvent>(ShowcaseEvent.Loading)

    init {
        event.value = ShowcaseEvent.Loading
        event.value = ShowcaseEvent.Success(stubList)
        this.viewModelScope.launch {
            loadProducts()
        }
    }

    private suspend fun loadProducts() {
        val response = api.getAllProducts()
        val body = response.body()
        Log.d("Resp", response.body().toString())
        if (response.isSuccessful && body != null) {
            event.value = ShowcaseEvent.Success(body.posts.map {
                Product(
                    id = it.id,
                    userId = it.userId,
                    title = it.title,
                    imageUrl = it.imagePath,
                    description = it.description,
                    favourites = false,
                    tags = tagDtoToString(it.tags)
                )
            })
        } else {
            event.value = ShowcaseEvent.Error
        }
    }


    private fun tagDtoToString(list: List<TagDto>): List<String> {
        var returnList = arrayListOf<String>()
        for (tag in list) {
            returnList.add(tag.tag)
        }
        return returnList
    }
}