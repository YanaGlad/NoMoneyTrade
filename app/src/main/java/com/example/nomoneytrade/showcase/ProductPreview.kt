package com.example.nomoneytrade.showcase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductPreview(
    val id: Int = -1,
    val userId: Int = -1,
    val title: String = "",
    val imageUrl: String = "",
    val description: String = "", //.substring(0,100) ?
    val favourites: Boolean = false, //favs
    val tags: List<String> = listOf(),
) : Parcelable