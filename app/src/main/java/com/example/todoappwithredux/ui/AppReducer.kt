package com.example.todoappwithredux.ui

import com.example.todoappwithredux.ReducerType

object AppReducer : ReducerType<AppState, AppAction> {
    override fun reduce(currentState: AppState, action: AppAction): AppState {
        return when (action) {
            is AppAction.AddTodo -> {
                val newTodoList = currentState.todos.plus(action.todo)
                AppState(newTodoList)
            }

            is AppAction.RefreshTodos -> {
                val refreshTodoList = currentState.todos
                AppState(refreshTodoList)
            }
        }
    }
}