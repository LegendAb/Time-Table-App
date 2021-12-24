package com.example.timetableapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.UserDatabase
import com.example.timetableapp.model.SetupUser
import com.example.timetableapp.repository.UserRepository
import com.example.timetableapp.model.User
import com.example.timetableapp.repository.SetupUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetupUserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<SetupUser>
    private val repository: SetupUserRepository

    init {
        val setupDao = UserDatabase.getDatabase(application).setupDao()
        repository = SetupUserRepository(setupDao)
        readAllData = repository.readAllData
    }

    fun addUser(setupUser: SetupUser){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(setupUser)
        }
    }

    fun updateUser(setupUser: SetupUser){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(setupUser)
        }
    }
}