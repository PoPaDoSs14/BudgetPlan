package com.example.budgetplan.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addUser(user: User)

    suspend fun getUser(id: Int): User

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)



    suspend fun addTask(task: Task)

    suspend fun getTask(id: Int): Task

    suspend fun getTasks(): Flow<List<Task>>

    suspend fun deleteTask(task: Task)
}