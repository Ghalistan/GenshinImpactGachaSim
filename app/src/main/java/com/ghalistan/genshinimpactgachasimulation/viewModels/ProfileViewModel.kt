package com.ghalistan.genshinimpactgachasimulation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ghalistan.genshinimpactgachasimulation.database.GachaDatabase
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
import com.ghalistan.genshinimpactgachasimulation.models.UserModel
import com.ghalistan.genshinimpactgachasimulation.repositories.RoomRepo

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val roomRepo: RoomRepo
    val user: LiveData<List<UserModel>>
    val item: LiveData<List<ItemModel>>

    init {
        val database = GachaDatabase.getDatabase(application, viewModelScope)
        val userDao = database.userDao()
        val itemDao = database.itemDao()
        roomRepo = RoomRepo(userDao, itemDao)
        user = roomRepo.getUser
        item = roomRepo.getItem
    }
}