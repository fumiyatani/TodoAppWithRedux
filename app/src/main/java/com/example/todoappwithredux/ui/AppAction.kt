package com.example.todoappwithredux.ui

import com.example.todoappwithredux.ActionType
import com.example.todoappwithredux.data.local.TodoEntity

/**
 * 状態変更を表現しているクラス
 * 定義済みアクション
 *   - リフレッシュ
 *   - 追加
 */
sealed class AppAction : ActionType {
    object RefreshTodos : AppAction()
    data class AddTodo(val todo: TodoEntity) : AppAction()
}
