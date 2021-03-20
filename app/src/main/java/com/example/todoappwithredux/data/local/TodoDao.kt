package com.example.todoappwithredux.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface TodoDao {
    @Query("SELECT * FROM tbl_todos")
    suspend fun getAllTodo(): List<TodoDao>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)
}