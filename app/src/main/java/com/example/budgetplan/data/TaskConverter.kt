package com.example.budgetplan.data

import androidx.room.TypeConverter
import com.example.budgetplan.domain.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromTask(task: Task?): String? {
        return gson.toJson(task)
    }

    @TypeConverter
    fun toTask(taskString: String?): Task? {
        if (taskString.isNullOrEmpty()) return null
        val type = object : TypeToken<Task>() {}.type
        return gson.fromJson(taskString, type)
    }
}