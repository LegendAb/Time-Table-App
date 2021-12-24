package com.example.timetableapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.SetupDao
import com.example.roomapp.data.UserDao
import com.example.timetableapp.model.SetupUser
import com.example.timetableapp.model.User

class SetupUserRepository(private val setupDao: SetupDao) {

    val readAllData: LiveData<SetupUser> = setupDao.readAllData()

    suspend fun addUser(setupUser: SetupUser){
        setupDao.addUser(setupUser)
    }

    suspend fun updateUser(setupUser: SetupUser){
        setupDao.updateUser(setupUser)
    }

}