package com.ghalistan.genshinimpactgachasimulation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ghalistan.genshinimpactgachasimulation.daos.UserDao
import com.ghalistan.genshinimpactgachasimulation.models.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class GachaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTACE: GachaDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): GachaDatabase {
            return INSTACE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GachaDatabase::class.java,
                    "user_database"
                )
                    .addCallback(userDatabaseCallback(scope))
                    .build()
                INSTACE = instance
                instance
            }
        }
    }

    private class userDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTACE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.userDao())
                }
            }
        }

        suspend fun populateDatabase(userDao: UserDao) {
            userDao.deleteAll()

            val user = UserModel("Default_Username", 0)
            userDao.insert(user)
        }
    }
}