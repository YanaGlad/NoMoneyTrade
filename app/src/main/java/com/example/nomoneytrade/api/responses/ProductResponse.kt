package com.example.nomoneytrade.api.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
class ProductResponse(
    val id: Long,
    val userId: Long,
    val title: String,
    val imageUrl: String,
    val description: String,
    val favourites: Boolean,
    val tags: List<String>,
    val exchangeTags: List<String>,
)