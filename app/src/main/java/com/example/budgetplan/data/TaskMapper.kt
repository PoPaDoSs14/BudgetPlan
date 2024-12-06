package com.example.budgetplan.data

import com.example.budgetplan.domain.Task

class TaskMapper {

    val taskTypeConverter = TaskTypeConverter()

    fun mapEntityToDbModel(task: Task): TaskDbModel{
        return TaskDbModel(
            id = task.id,
            isProfit = task.isProfit,
            value = task.value,
            type = taskTypeConverter.fromTaskType(task.type)!!
        )
    }

    fun mapDbModelToEntity(taskDbModel: TaskDbModel): Task {
        return Task(
            id = taskDbModel.id,
            isProfit = taskDbModel.isProfit,
            value = taskDbModel.value,
            type = taskTypeConverter.toTaskType(taskDbModel.type)!!
        )
    }


    fun mapListDbModelToListEntity(list: List<TaskDbModel>): List<Task> = list.map {
        mapDbModelToEntity(it)
    }
}