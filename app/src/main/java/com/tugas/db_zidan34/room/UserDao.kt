package com.tugas.roomdb_zidan34.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun adduser(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM UserTabel")
    suspend fun getusers():List<User>

    @Query("SELECT * FROM UserTabel WHERE id=:user_id")
    suspend fun getuser(user_id: Int): List<User>



}