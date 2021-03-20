package com.example.todoappwithredux.ui

import com.example.todoappwithredux.StateType
import com.example.todoappwithredux.data.local.TodoEntity

/**
 * アプリの状態を表現するクラス
 */
data class AppState(
    val todos: List<TodoEntity> = emptyList()
) : StateType
