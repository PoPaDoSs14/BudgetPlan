package com.example.budgetplan.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TaskDbModel::class, UserDbModel::class], version = 1, exportSchema = false)
@TypeConverters(TaskTypeConverter::class)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao

    companion object{
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private val DB_NAME = "db.db"

        fun getInstance(application: Application): AppDatabase{
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME)
                .build()

            INSTANCE = db

            return db
        }
    }
}