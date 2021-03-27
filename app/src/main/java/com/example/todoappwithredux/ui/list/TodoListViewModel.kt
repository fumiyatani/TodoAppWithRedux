package com.example.todoappwithredux.ui.list

import androidx.lifecycle.*
import com.example.todoappwithredux.data.local.TodoEntity
import com.example.todoappwithredux.ui.AppStore
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoListViewModel @Inject constructor(
    private val appStore: AppStore
) : ViewModel() {

    // Navigation event
    private val navigationEventChannel = Channel<NavigationEvent>(Channel.BUFFERED)
    val navigationEvents = navigationEventChannel.receiveAsFlow()

    // Contents list event
    private val _todoList = MutableLiveData<List<TodoEntity>>().also {
        viewModelScope.launch {
            appStore.observable().collectLatest { state ->
                it.value = state.todos
            }
        }
    }
    val todoList: LiveData<List<TodoEntity>> = _todoList

    fun onClickAdd() {
        viewModelScope.launch {
            navigationEventChannel.send(NavigationEvent.NavigateToAdd)
        }
    }
}

sealed class NavigationEvent() {
    object NavigateToAdd : NavigationEvent()
}