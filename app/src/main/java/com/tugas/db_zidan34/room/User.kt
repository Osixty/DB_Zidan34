package com.tugas.roomdb_zidan34.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTabel")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nama: String,
    val sekolah: String
)