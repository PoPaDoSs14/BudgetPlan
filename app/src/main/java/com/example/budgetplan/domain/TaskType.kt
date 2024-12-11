package com.example.budgetplan.domain

enum class TaskType {
    FOOD, TAXES, MEDICINES;

    companion object {
        fun getValues(): Array<String> {
            return values().map { it.name }.toTypedArray()
        }
    }
}