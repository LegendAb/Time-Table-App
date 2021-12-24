package com.example.timetableapp.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setup_table")
data class SetupUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val institution: String,
    val name: String,
    val department: String,
    val level: String,
    //val profilePhoto: Bitmap,

    )
