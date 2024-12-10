package com.example.budgetplan.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)


    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTask(task)
        }
    }

    fun isProfit(number: Int): Boolean{
        if (number >= 0 ){
            return true
        }
        else return false
    }
}