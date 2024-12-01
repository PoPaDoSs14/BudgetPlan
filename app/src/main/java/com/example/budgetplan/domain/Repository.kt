package com.example.budgetplan.domain

interface Repository {

    suspend fun addUser(user: User)

    suspend fun getUser(id: Int): User

    suspend fun deleteUser(user: User)



    suspend fun addTask(task: Task)

    suspend fun getTask(id: Int): Task

    suspend fun getTasks(): List<Task>

    suspend fun deleteTask(task: Task)
}