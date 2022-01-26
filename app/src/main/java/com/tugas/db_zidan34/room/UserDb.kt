package com.tugas.db_zidan34.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tugas.roomdb_zidan34.data.User
import com.tugas.roomdb_zidan34.data.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDb: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var instance: UserDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            UserDb::class.java,
            "user_data"
        ).build()
    }
}