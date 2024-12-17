package com.example.budgetplan.domain

data class User(
    val id: Int = 0,
    val name: String,
    var money: Int,
    val monthProfit: Int,
    val monthLosses: Int,
    var lastTask: Task?
)

