package com.ghalistan.genshinimpactgachasimulation

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ghalistan.genshinimpactgachasimulation.daos.BannerDao

public abstract class GachaDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerDao

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