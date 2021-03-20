package com.example.todoappwithredux.data.local

import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
}