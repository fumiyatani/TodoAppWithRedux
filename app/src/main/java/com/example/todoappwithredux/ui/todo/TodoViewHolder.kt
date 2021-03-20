package com.example.todoappwithredux.ui.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithredux.data.local.TodoEntity
import com.example.todoappwithredux.databinding.ItemTodoBinding

class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(todo: TodoEntity) {
        binding.todo = todo
    }

    companion object {
        fun createViewHolder(parent: ViewGroup): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemTodoBinding.inflate(inflater, parent, false)

            return TodoViewHolder(binding)
        }
    }
}