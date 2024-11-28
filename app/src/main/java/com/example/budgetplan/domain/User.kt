package com.example.budgetplan.domain

data class User(
    val name: String,
    val money: Int,
    val monthProfit: Int,
    val monthLosses: Int,
    val lastTask: Task
)
