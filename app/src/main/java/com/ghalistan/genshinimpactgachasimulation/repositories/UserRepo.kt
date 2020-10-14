package com.ghalistan.genshinimpactgachasimulation.repositories

import com.ghalistan.genshinimpactgachasimulation.daos.UserDao

class UserRepo(private val userDao: UserDao) {
    val getUser = userDao.getAll()
}