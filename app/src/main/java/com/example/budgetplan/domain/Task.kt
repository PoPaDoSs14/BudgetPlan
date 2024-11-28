package com.example.budgetplan.domain

data class Task(
    val isProfit: Boolean,
    val value: Int,
    val type: TaskType,
)
