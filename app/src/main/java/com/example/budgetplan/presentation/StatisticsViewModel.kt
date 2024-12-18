package com.example.budgetplan.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.Task
import com.example.budgetplan.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application, val lifecycleOwner: LifecycleOwner): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val _user = MutableLiveData<User>()
    private val _remaingMoney = MutableLiveData<String>()
    private val _income = MutableLiveData<Float>()
    private val _expenses = MutableLiveData<Float>()
    private val _tasks = MutableLiveData<List<Task>>()
    val income: LiveData<Float> get() = _income
    val expenses: LiveData<Float> get() = _expenses
    val user: LiveData<User> get() = _user
    val remaingMoney: LiveData<String> get() = _remaingMoney
    val tasks: LiveData<List<Task>> get() = _tasks

    init {
        getTasks()
        getUser()
        observeUser()
        getIncome()
        getExpenses()
    }

    fun getIncome() {
        _user.observe(lifecycleOwner) { user ->
            _income.postValue(user?.monthProfit?.toFloat() ?: 0f)
        }
    }

    fun getExpenses() {
        _user.observe(lifecycleOwner) { user ->
            _expenses.postValue(user?.monthLosses?.toFloat() ?: 0f)
        }
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedUser = repo.getUser(USER_ID)
            _user.postValue(fetchedUser)
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            repo.getTasks()
                .flowOn(Dispatchers.IO)
                .collect { taskList ->
                    _tasks.postValue(taskList)
                }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = repo.getUser(USER_ID)!!.id
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
            val user = repo.getUser(USER_ID)

            val remainingMoneyString = remaingMoney.value ?: "0"

            val remainingMoneyValue = extractMoneyFromString(remainingMoneyString)

            val newMoney = money + remainingMoneyValue
            val updateUser = User(user!!.id, user.name, newMoney, user.monthProfit, user.monthLosses, user.lastTask)
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

    companion object{
        const val USER_ID = 1
    }
}