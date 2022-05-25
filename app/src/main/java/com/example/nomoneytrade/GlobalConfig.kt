package com.example.nomoneytrade

import com.example.nomoneytrade.profile.entity.Profile
import com.example.nomoneytrade.showcase.ProductPreview

var CURRENT_USER_ID = -1

val stubList =  listOf(
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

val stubUser = Profile(id = 1,
    username = "Vova",
    fio = "Abubakirov Vladimir",
    email = "mailemail@yandex.ru",
    iconUrl = "",
    city = "Москва",
    address = "Метро ЮгоЗападная",
    phoneNumber = "8 800 555 35 35",
)