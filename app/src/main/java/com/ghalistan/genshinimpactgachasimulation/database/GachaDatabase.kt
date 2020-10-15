package com.ghalistan.genshinimpactgachasimulation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ghalistan.genshinimpactgachasimulation.daos.ItemDao
import com.ghalistan.genshinimpactgachasimulation.daos.UserDao
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
import com.ghalistan.genshinimpactgachasimulation.models.UserModel
import com.ghalistan.genshinimpactgachasimulation.utils.itemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UserModel::class, ItemModel::class], version = 1, exportSchema = false)
abstract class GachaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao

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
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTACE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao(), database.itemDao())
                }
            }
        }

        suspend fun populateDatabase(userDao: UserDao, itemDao: ItemDao) {
            userDao.deleteAll()
            itemDao.deleteAll()

            val user = UserModel("Default_Username", 0, 90, 10)
            userDao.insert(user)

            val item = itemModel
            itemDao.insertAll(item)
        }
    }
}