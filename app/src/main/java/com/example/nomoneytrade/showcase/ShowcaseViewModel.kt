package com.example.nomoneytrade.showcase

import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.api.AuthApi
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.mvi.event.ShowcaseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ShowcaseViewModel @Inject constructor() : ViewModel()  {

    val event = MutableStateFlow<ShowcaseEvent>(ShowcaseEvent.Loading)


    init {
        //Stub!
        event.value = ShowcaseEvent.Success(
            listOf(
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
                    description = "Рюкзак от самого лучшего банка ыыыыыыыыыыыыыыыыыыыыыыыыыыыыыыыы",
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
                    tags = listOf("spacex", "космос", "модельки") //более 3 тегов только с подпиской/платное объявление
                )
            )
        )
    }

    suspend fun loadProducts() {

    }
}