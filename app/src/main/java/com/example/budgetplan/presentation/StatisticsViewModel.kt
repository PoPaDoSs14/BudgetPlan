package com.example.budgetplan.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)

    fun getIncome(): Float{
        var income = 0f
        viewModelScope.launch(Dispatchers.IO) {
            income = repo.getUser(0).monthProfit.toFloat()
        }
        return income
    }

    fun getExpenses(): Float{
        var expenses = 0f
        viewModelScope.launch(Dispatchers.IO) {
            expenses = repo.getUser(0).monthLosses.toFloat()
        }
        return expenses
    }
}