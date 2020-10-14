package com.ghalistan.genshinimpactgachasimulation.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghalistan.genshinimpactgachasimulation.models.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<UserModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserModel)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}