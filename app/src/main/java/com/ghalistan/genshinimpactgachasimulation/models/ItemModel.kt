package com.ghalistan.genshinimpactgachasimulation.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "item_owned")
data class ItemModel(
    @PrimaryKey val name: String = "",
    val type: String = "",
    val element: String = "",
    val rarity: Int = 0,
    val itemPict: String = ""
) : Parcelable