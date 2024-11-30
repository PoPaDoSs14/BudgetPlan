package com.example.budgetplan.data

import android.app.Application
import com.example.budgetplan.domain.Repository
import com.example.budgetplan.domain.Task
import com.example.budgetplan.domain.User

class RepositoryImpl(private val application: Application): Repository {
    override suspend fun addUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun addTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }
}