package com.ghalistan.genshinimpactgachasimulation.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey val username: String,
)