package com.ghalistan.genshinimpactgachasimulation.repositories

import com.ghalistan.genshinimpactgachasimulation.daos.ItemDao
import com.ghalistan.genshinimpactgachasimulation.daos.UserDao
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel

class RoomRepo(private val userDao: UserDao, private val itemDao: ItemDao) {
    val getUser = userDao.getAll()
    val getItem = itemDao.getAll()

    suspend fun insertItems(item: List<ItemModel>) {
        itemDao.insertAll(item)
    }
}