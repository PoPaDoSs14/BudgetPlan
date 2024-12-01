package com.example.budgetplan.data

import androidx.room.TypeConverter
import com.example.budgetplan.domain.TaskType

class TaskTypeConverter {

    @TypeConverter
    fun fromTaskType(taskType: TaskType?): String? {
        return taskType?.name
    }

    @TypeConverter
    fun toTaskType(taskTypeString: String?): TaskType? {
        return taskTypeString?.let { TaskType.valueOf(it) }
    }
}