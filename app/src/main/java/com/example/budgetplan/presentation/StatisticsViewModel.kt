package com.example.budgetplan.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.Task
import com.example.budgetplan.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val _user = MutableLiveData<User>()
    private val _remaingMoney = MutableLiveData<String>()
    private val _tasks = MutableLiveData<List<Task>>()
    val user: LiveData<User> get() = _user
    val remaingMoney: LiveData<String> get() = _remaingMoney
    val tasks: LiveData<List<Task>> get() = _tasks

    init {
        getTasks()
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

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTasks().map { _tasks.postValue(it) } // TODO здесь ошибка
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = repo.getUser(1).id
            val updateUser = User(
                userId,
                name = user.name,
                money = user.money,
                monthProfit = user.monthProfit,
                monthLosses = user.monthLosses,
                lastTask = user.lastTask
            )
            repo.updateUser(updateUser)
        }
    }

    fun updateMoney(money: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repo.getUser(1)

            val remainingMoneyString = remaingMoney.value ?: "0"

            val remainingMoneyValue = extractMoneyFromString(remainingMoneyString)

            val newMoney = money + remainingMoneyValue
            val updateUser = User(user.id, user.name, newMoney, user.monthProfit, user.monthLosses, user.lastTask)
            updateUser(updateUser)
        }
    }

    fun addRemaingMoney(money: String) {
        val oldMoneyLenght = remaingMoney.value?.length ?: 0
        val oldMoney = remaingMoney.value?.substring(16, oldMoneyLenght - 1)?.trim()?.toInt()
        val newMoney = oldMoney?.plus(money.toInt())
        _remaingMoney.postValue("Осталось денег: $newMoney ₽")
    }

    private fun extractMoneyFromString(moneyString: String): Int {
        return moneyString.replace(Regex("[^0-9]"), "").toIntOrNull() ?: 0
    }

    private fun observeUser() {
        user.observeForever { user ->
            val money = user?.money?.toString() ?: "0"
            _remaingMoney.postValue("Осталось денег: $money ₽")
        }
    }
}