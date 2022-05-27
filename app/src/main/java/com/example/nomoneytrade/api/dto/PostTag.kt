package com.example.nomoneytrade.api.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PostTag(
    val id: Int,
    val tag: String,
) : Parcelable