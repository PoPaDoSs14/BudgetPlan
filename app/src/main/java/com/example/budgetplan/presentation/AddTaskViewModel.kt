package com.example.budgetplan.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetplan.data.RepositoryImpl
import com.example.budgetplan.domain.Task
import com.example.budgetplan.domain.TaskType
import com.example.budgetplan.domain.User
import com.example.budgetplan.presentation.StatisticsViewModel.Companion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)


    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            val user = repo.getUser(USER_ID)
            user!!.lastTask = task
            user.money += task.value
            if (task.isProfit){
                user.monthProfit += task.value
            } else{
                user.monthLosses += task.value
            }
            updateUser(user)
            repo.addTask(task)
        }
    }

    fun isProfit(number: Int): Boolean{
        if (number >= 0 ){
            return true
        }
        else return false
    }

    fun getTaskType(type: String): TaskType {
        return when (type.toUpperCase()) {
            "FOOD" -> TaskType.FOOD
            "TAXES" -> TaskType.TAXES
            "MEDICINES" -> TaskType.MEDICINES
            "SALARY" -> TaskType.SALARY
            "OTHER" -> TaskType.OTHER
            else -> throw IllegalArgumentException("Unknown task type: $type")
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = repo.getUser(StatisticsViewModel.USER_ID)!!.id
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

    companion object{
        const val USER_ID = 1
    }
}