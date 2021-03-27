package com.example.todoappwithredux.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithredux.R
import com.example.todoappwithredux.databinding.FragmentTodoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class TodoListFragment : Fragment() {

    private val todoAdapter = TodoAdapter()

    private lateinit var binding: FragmentTodoListBinding

    @Inject
    lateinit var todoListViewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupFloatingActionButton()

        observeLiveData()
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = todoAdapter
        }
    }

    private fun setupFloatingActionButton() {
        binding.fab.setOnClickListener {
            todoListViewModel.onClickAdd()
//            appStore.dispatch(AppAction.AddTodo(TodoEntity(title = "適当なやつ")))
        }
    }

    private fun observeLiveData() {
        todoListViewModel.todoList.observe(viewLifecycleOwner) { todos ->
            todoAdapter.submitList(todos)
        }

        todoListViewModel.navigationEvents
            .onEach {
                when (it) {
                    is NavigationEvent.NavigateToAdd -> {
                        findNavController().navigate(R.id.navigateToTodoAddFragment)
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}