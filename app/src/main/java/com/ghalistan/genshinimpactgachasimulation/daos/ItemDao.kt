package com.ghalistan.genshinimpactgachasimulation.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel

@Dao
interface ItemDao {
    @Query("SELECT * FROM item_owned")
    fun getAll(): LiveData<List<ItemModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ItemModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItemModel>)

    @Query("DELETE FROM item_owned")
    suspend fun deleteAll()
}