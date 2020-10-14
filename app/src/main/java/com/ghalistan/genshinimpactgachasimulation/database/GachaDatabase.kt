package com.ghalistan.genshinimpactgachasimulation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ghalistan.genshinimpactgachasimulation.daos.UserDao
import com.ghalistan.genshinimpactgachasimulation.models.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class GachaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTACE: GachaDatabase? = null

        fun getDatabase(context: Context): GachaDatabase {
            val tempInstance = INSTACE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GachaDatabase::class.java,
                    "Gacha_Database"
                ).build()
                INSTACE = instance
                return instance
            }
        }
    }
}