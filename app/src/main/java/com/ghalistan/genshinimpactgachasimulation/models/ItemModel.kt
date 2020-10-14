package com.ghalistan.genshinimpactgachasimulation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemModel(
    val name: String = "",
    val type: String = "",
    val element: String = "",
    val rarity: Int = 0,
    val itemPict: String = ""
) : Parcelable