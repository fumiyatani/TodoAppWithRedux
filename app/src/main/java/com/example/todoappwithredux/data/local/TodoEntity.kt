package com.example.todoappwithredux.data.local

import androidx.room.Entity
import java.util.*

@Entity(tableName = "tbl_todos")
data class TodoEntity(
    val id: String = UUID.randomUUID().toString(),
    val title: String
)
