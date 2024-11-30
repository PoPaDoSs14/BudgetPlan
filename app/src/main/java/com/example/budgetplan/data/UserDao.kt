package com.example.budgetplan.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun addUser(userDbModel: UserDbModel)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserDbModel>>

    @Query("DELETE FROM users WHERE id=:id")
    fun deleteUser(id: Int)

    @Update
    fun update(userDbModel: UserDbModel)

    @Query("SELECT * FROM users WHERE id=:id")
    fun getUser(id: Int): UserDbModel
}