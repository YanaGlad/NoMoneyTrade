package com.example.nomoneytrade.showcase

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.mvi.event.ShowcaseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ShowcaseViewModel @Inject constructor() : ViewModel()  {

    val event = MutableStateFlow<ShowcaseEvent>(ShowcaseEvent.Loading)


    init {
        val list =  listOf(
            ProductPreview(
                id = 1,
                userId = 12,
                title = "Рюкзак Samsung It School",
                imageUrl = "",
                description = "Шикарный рюкзак",
                favourites = false,
                tags = listOf("Вещи", "рюкзаки", "сумки") //более 3 тегов только с подпиской/платное объявление
            ),
            ProductPreview(
                id = 2,
                userId = 7,
                title = "Рюкзак Тинькофф",
                imageUrl = "",
                description = "Рюкзак от самого лучшего банка ыыыыыыweqeqweqwqweqwewqqwwdwыыыыыыыыыыыыыыыыыыыыыыыыыы",
                favourites = false,
                tags = listOf("вещи", "рюкзаки", "тинькофф") //более 3 тегов только с подпиской/платное объявление
            ),
            ProductPreview(
                id = 3,
                userId = 7,
                title = "Моделька Falcon Heavy",
                imageUrl = "",
                description = "Модель ракеты SpaceX FalconHeavy из пластика",
                favourites = false,
                tags = listOf("spacex", "космос", "модельки", "ракета", "falcon heavy", "пластик") //более 3 тегов только с подпиской/платное объявление
            ),
            ProductPreview(
                id = 4,
                userId = 3,
                title = "Ручка it школа",
                imageUrl = "",
                description = "Бесценная ручка",
                favourites = false,
                tags = listOf("ручка", "канцелярия", "самсунг")
            )
        )
        //Stub!
        event.value = ShowcaseEvent.Success(
            list
        )
        list.forEach {
            Log.d("TATAT", "Len: ${it.description.length}")
        }
    }

    suspend fun loadProducts() {

    }
}