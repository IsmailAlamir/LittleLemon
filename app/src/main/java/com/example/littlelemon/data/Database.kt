package com.example.littlelemon.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase


@Entity
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String,
)


@Database(entities = [MenuItemRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}




object DatabaseSingleton {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        synchronized(this) {
            return database ?: buildDatabase(context).also {
                database = it
            }
        }
    }

    private fun buildDatabase(context: Context): AppDatabase {

        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "database"
        ).build()
    }
}


