package com.example.budgetplan.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.budgetplan.domain.TaskType

@Entity("Tasks")
data class TaskDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val isProfit: Boolean,
    val value: Int,
    val type: TaskType,
)
