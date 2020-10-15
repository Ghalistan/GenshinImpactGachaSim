package com.ghalistan.genshinimpactgachasimulation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ghalistan.genshinimpactgachasimulation.database.GachaDatabase
import com.ghalistan.genshinimpactgachasimulation.models.UserModel
import com.ghalistan.genshinimpactgachasimulation.repositories.RoomRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RoomRepo
    val user: LiveData<List<UserModel>>

    init {
        val userDao = GachaDatabase.getDatabase(application, viewModelScope).userDao()
        val itemDao = GachaDatabase.getDatabase(application, viewModelScope).itemDao()
        repository = RoomRepo(userDao, itemDao)
        user = repository.getUser
    }
}