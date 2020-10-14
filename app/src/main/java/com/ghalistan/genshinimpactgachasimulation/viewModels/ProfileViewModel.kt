package com.ghalistan.genshinimpactgachasimulation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ghalistan.genshinimpactgachasimulation.database.GachaDatabase
import com.ghalistan.genshinimpactgachasimulation.models.UserModel
import com.ghalistan.genshinimpactgachasimulation.repositories.UserRepo

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepo: UserRepo
    val user: LiveData<List<UserModel>>

    init {
        val userDao = GachaDatabase.getDatabase(application, viewModelScope).userDao()
        userRepo = UserRepo(userDao)
        user = userRepo.getUser
    }
}