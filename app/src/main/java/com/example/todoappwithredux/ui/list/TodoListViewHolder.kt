package com.example.todoappwithredux.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithredux.data.local.TodoEntity
import com.example.todoappwithredux.databinding.ItemTodoBinding

class TodoListViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(todo: TodoEntity) {
        binding.todo = todo
    }

    companion object {
        fun createViewHolder(parent: ViewGroup): TodoListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemTodoBinding.inflate(inflater, parent, false)

            return TodoListViewHolder(binding)
        }
    }
}