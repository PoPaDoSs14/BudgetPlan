package com.example.budgetplan.data

import android.app.Application
import com.example.budgetplan.domain.Repository
import com.example.budgetplan.domain.Task
import com.example.budgetplan.domain.User

class RepositoryImpl(private val application: Application): Repository {


    val taskDao = AppDatabase.getInstance(application).taskDao()
    val userDao = AppDatabase.getInstance(application).userDao()
    val userMapper = UserMapper()
    val taskMapper = UserMapper()

    override suspend fun addUser(user: User) {
        userDao.addUser(userMapper.mapEntityToDbModel(user))
    }

    override suspend fun getUser(id: Int): User {
        return userMapper.mapDbModelToEntity(userDao.getUser(id))
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.id)
    }

    override suspend fun addTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(id: Int): Task {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }
}