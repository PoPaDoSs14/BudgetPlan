package com.example.budgetplan.data

import android.app.Application
import com.example.budgetplan.domain.Repository
import com.example.budgetplan.domain.Task
import com.example.budgetplan.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val application: Application): Repository {


    val taskDao = AppDatabase.getInstance(application).taskDao()
    val userDao = AppDatabase.getInstance(application).userDao()
    val userMapper = UserMapper()
    val taskMapper = TaskMapper()

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
        taskDao.addTask(taskMapper.mapEntityToDbModel(task))
    }

    override suspend fun getTask(id: Int): Task {
        return taskMapper.mapDbModelToEntity(taskDao.getTask(id))
    }

    override suspend fun getTasks(): Flow<List<Task>> = taskDao.getTasks().map {
        taskMapper.mapListDbModelToListEntity(it)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.id)
    }
}