package com.example.budgetplan.presentation

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }

    fun firstLogin(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.getUser(1) != null) {
                val intent = Intent(context, StatisticsActivity::class.java)
                withContext(Dispatchers.Main) {
                    context.startActivity(intent)
                }
            }
        }
    }
}