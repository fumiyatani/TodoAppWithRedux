package com.example.todoappwithredux.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithredux.data.local.TodoEntity
import com.example.todoappwithredux.databinding.FragmentTodoBinding
import com.example.todoappwithredux.ui.AppAction
import com.example.todoappwithredux.ui.AppStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoFragment : Fragment() {

    // ViewModel を作っていないので作成
    private val cancellationJob = Job()
    private val coroutineScope = CoroutineScope(cancellationJob + Dispatchers.Main)

    // View では Store を保持する
    private val appStore = AppStore.getInstance()

    private lateinit var binding: FragmentTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupFloatingActionButton()
//        setupSwipeRefresh()
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val todoAdapter = TodoAdapter()

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = todoAdapter
        }

        coroutineScope.launch {
            appStore.observable().collectLatest { state ->
                todoAdapter.submitList(state.todos)
            }
        }
    }

    private fun setupFloatingActionButton() {
        binding.fab.setOnClickListener {
            coroutineScope.launch {
                appStore.dispatch(AppAction.AddTodo(TodoEntity(title = "適当なやつ")))
            }
        }
    }

    // Flow の性質上いらない可能性が高い。
    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            coroutineScope.launch {
                appStore.dispatch(AppAction.RefreshTodos)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onPause() {
        cancellationJob.cancel()

        super.onPause()
    }
}