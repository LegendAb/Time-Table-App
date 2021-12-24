package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.timetableapp.model.SetupUser
import com.example.timetableapp.model.User

@Dao
interface SetupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(setupUser: SetupUser)

    @Update
    suspend fun updateUser(setupUser: SetupUser)


    @Query("SELECT * FROM setup_table ORDER BY id ASC")
    fun readAllData(): LiveData<SetupUser>

}