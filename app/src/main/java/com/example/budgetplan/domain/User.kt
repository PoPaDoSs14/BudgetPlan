package com.example.budgetplan.domain

data class User(
    val id: Int = 0,
    val name: String,
    var money: Int,
    var monthProfit: Int,
    var monthLosses: Int,
    var lastTask: Task?
)

