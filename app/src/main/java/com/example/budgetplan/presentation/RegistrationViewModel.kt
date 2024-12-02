package com.example.budgetplan.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }
}