package com.ghalistan.genshinimpactgachasimulation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullableModel(
    val pullableObject: ItemModel = ItemModel(),
    val featuredItem: Boolean = false,
    val objectType: String = ""
) : Parcelable