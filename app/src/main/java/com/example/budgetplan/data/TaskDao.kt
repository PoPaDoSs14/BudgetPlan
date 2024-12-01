package com.example.budgetplan.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.budgetplan.domain.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    fun addTask(taskDbModel: TaskDbModel)

    @Query("SELECT * FROM tasks WHERE id=:id")
    fun getTask(id: Int): TaskDbModel

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskDbModel>>

    @Query("DELETE FROM tasks WHERE id=:id")
    fun deleteTask(id: Int)

}