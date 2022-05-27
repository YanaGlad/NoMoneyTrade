package com.example.nomoneytrade.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product( //TODO Product и ProductRequest
    val id: Long = -1,
    val userId: Long = -1,
    val title: String = "",
    val imageUrl: String = "",
    val description: String = "", //.substring(0,100) ?
    val favourites: Boolean = false, //favs
    val tags: List<String> = listOf(),
    val exchangeTags: List<String> = listOf()
) : Parcelable