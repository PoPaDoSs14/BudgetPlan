package com.example.budgetplan.domain

data class Task(
    val id: Int,
    val isProfit: Boolean,
    val value: Int,
    val type: TaskType,
)
