package com.ghalistan.genshinimpactgachasimulation.daos

import androidx.room.Dao
import androidx.room.Query
import com.ghalistan.genshinimpactgachasimulation.models.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<UserModel>
}