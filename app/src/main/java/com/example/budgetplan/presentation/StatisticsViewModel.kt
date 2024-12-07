package com.example.budgetplan.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val _user = MutableLiveData<User>()
    private val _remaingMoney = MutableLiveData<String>()
    val user: LiveData<User> get() = _user
    val remaingMoney: LiveData<String> get() = _remaingMoney

    init {
        getUser()
        observeUser()
    }

    fun getIncome(): LiveData<Float> {
        val income = MutableLiveData<Float>()
        _user.observeForever { user ->
            income.value = user?.monthProfit?.toFloat() ?: 0f
        }
        return income
    }

    fun getExpenses(): LiveData<Float> {
        val expenses = MutableLiveData<Float>()
        _user.observeForever { user ->
            expenses.value = user?.monthLosses?.toFloat() ?: 0f
        }
        return expenses
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedUser = repo.getUser(1)
            _user.postValue(fetchedUser)
        }
    }

    private fun observeUser() {
        user.observeForever { user ->
            val money = user?.money?.toString() ?: "0"
            _remaingMoney.postValue("Осталось денег: $money ₽")
        }
    }
}