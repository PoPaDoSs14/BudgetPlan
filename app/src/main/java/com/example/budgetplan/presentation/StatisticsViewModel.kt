package com.example.budgetplan.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    lateinit var user: User

    init {
        getUser()
    }

    fun getIncome(): Float{
        var income = 0f
        viewModelScope.launch(Dispatchers.IO) {
            income = user.monthProfit.toFloat()
        }
        return income
    }

    fun getExpenses(): Float{
        var expenses = 0f
        viewModelScope.launch(Dispatchers.IO) {
            expenses = user.monthLosses.toFloat()
        }
        return expenses
    }


    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            user = repo.getUser(0)
        }
    }
}