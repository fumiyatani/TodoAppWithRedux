package com.example.todoappwithredux.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todoappwithredux.data.local.TodoEntity

class TodoAdapter(
    diffUtl: DiffUtil.ItemCallback<TodoEntity> = defaultDiffUtil
) : ListAdapter<TodoEntity, TodoListViewHolder>(diffUtl) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        return TodoListViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

private val defaultDiffUtil = object : DiffUtil.ItemCallback<TodoEntity>() {
    override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem.id == newItem.id
    }
}