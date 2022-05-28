package com.example.nomoneytrade

import com.example.nomoneytrade.entity.User
import com.example.nomoneytrade.entity.Product

var CURRENT_USER_ID = -1L


val tinkoffBag = Product(
    id = 1,
    userId = 12,
    title = "Рюкзак Samsung It School",
    imageUrl = "https://sun9-east.userapi.com/sun9-20/s/v1/if2/4KUnGZKvbGCNDxCZEpg0-JVZV8X2nz70JU102leWmEkuMRAIVqnxz6RZf-tO6aaYaQDFr7RkK3vPOMHcEMKf-44S.jpg?size=2560x1920&quality=95&type=album",
    description = "Шикарный рюкзак",
    favourites = false,
    tags = listOf("Вещи", "рюкзаки", "сумки"), //более 3 тегов только с подпиской/платное объявление,
    exchangeTags = listOf("макбук", "рюкзак тинькофф"),
)

val alienMug = Product(
    id = 77,
    userId = 12,
    title = "Кружка инопланетянин",
    imageUrl = "https://sun9-east.userapi.com/sun9-20/s/v1/if2/4KUnGZKvbGCNDxCZEpg0-JVZV8X2nz70JU102leWmEkuMRAIVqnxz6RZf-tO6aaYaQDFr7RkK3vPOMHcEMKf-44S.jpg?size=2560x1920&quality=95&type=album",
    description = "Шикарный рюкзак",
    favourites = false,
    tags = listOf("кружка", "посуда", "инопланетяне"), //более 3 тегов только с подпиской/платное объявление,
    exchangeTags = listOf("книга", "монополия", "тетрадь"),
)

val samsungPen = Product(
    id = 4,
    userId = 1,
    title = "Ручка it школа",
    imageUrl = "https://sun9-east.userapi.com/sun9-60/s/v1/if2/EHplOy14ciNYBzdgBRAcEgzy_dpyvvDuOa_LAOklnQT8CTyPhKIFXYPqWQWGCzXgMkip7w-LgyhHtfesnuoh49Lz.jpg?size=1620x2160&quality=96&type=album",
    description = "Бесценная ручка",
    favourites = false,
    tags = listOf("ручка", "канцелярия", "самсунг"),
    exchangeTags = listOf("карандаш", "рюкзак самсунг"),
)

val stubList = listOf(
    tinkoffBag,
    Product(
        id = 2,
        userId = 7,
        title = "Рюкзак Тинькофф",
        imageUrl = "https://sun9-east.userapi.com/sun9-75/s/v1/if2/C1NnmI9UO3QpuDf4za9Kt9XooGMzRDbpk1WrfXdH918RfqKFdg1jNypW9Wfv6m1U7D8bhU8_KpsMlBcwy8H1wjOl.jpg?size=1200x1600&quality=95&type=album",
        description = "Рюкзак от самого лучшего банка ыыыыыыweqeqweqwqweqwewqqwwdwыыыыыыыыыыыыыыыыыыыыыыыыыы",
        favourites = false,
        tags = listOf("вещи", "рюкзаки", "тинькофф"), //более 3 тегов только с подпиской/платное объявление
        exchangeTags = listOf("пенал самсунг", "стикеры"),
    ),
    Product(
        id = 3,
        userId = 7,
        title = "Моделька Falcon Heavy",
        imageUrl = "https://sun9-east.userapi.com/sun9-75/s/v1/if2/C1NnmI9UO3QpuDf4za9Kt9XooGMzRDbpk1WrfXdH918RfqKFdg1jNypW9Wfv6m1U7D8bhU8_KpsMlBcwy8H1wjOl.jpg?size=1200x1600&quality=95&type=album",
        description = "Модель ракеты SpaceX FalconHeavy из пластика",
        favourites = false,
        tags = listOf("spacex", "космос", "модельки", "ракета", "falcon heavy", "пластик"),//более 3 тегов только с подпиской/платное объявление
        exchangeTags = listOf("старшип", "starship", "кружка"),
    ),
    samsungPen,
)

val stubUser = User(
    id = 1,
    username = "Vova",
    fio = "",
    email = "mailemail@yandex.ru",
    iconUrl = "https://sun9-west.userapi.com/sun9-10/s/v1/if2/wUqxyUpWCsLumpLhdL6MHfOvWdGXYsR9B4HPuiZ-ZH9BlFo4tWZcPA-5C5o9ozGVKSxBxdS1zZZ8BxU-ogsG-gPc.jpg?size=2560x1440&quality=96&type=album",
    phoneNumber = "8 800 555 35 35",
)

val stubMyUser = User(
    id = 12,
    username = "Y Glad",
    fio = "Yana Gladkikh",
    email = "yana@tink.ru",
    iconUrl = "https://sun9-west.userapi.com/sun9-10/s/v1/if2/CD95zKfRD7sMS1uCn74Q2azAwyAylz010GDGCOsjMxXkpGfZmacIr56BCA9SmWJ1DQFwVRlVXalzq_EwWSrzS4rS.jpg?size=688x631&quality=95&type=album",
    phoneNumber = "8 777 555 77 35",
)