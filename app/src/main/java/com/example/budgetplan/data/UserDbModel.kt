package com.example.budgetplan.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.budgetplan.domain.Task

@Entity("Users")
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val money: Int,
    val monthProfit: Int,
    val monthLosses: Int,
    val lastTask: String
)
