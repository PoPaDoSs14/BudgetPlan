package com.example.budgetplan.domain

interface Repository {

    suspend fun addUser(user: User)

    suspend fun getUser(id: Int)

    suspend fun deleteUser(user: User)



    suspend fun addTask(task: Task)

    suspend fun getTask(id: Int)

    suspend fun deleteTask(task: Task)
}