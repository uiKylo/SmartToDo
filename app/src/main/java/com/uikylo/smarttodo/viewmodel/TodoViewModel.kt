package com.uikylo.smarttodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.uikylo.smarttodo.data.Priority
import com.uikylo.smarttodo.data.Todo
import com.uikylo.smarttodo.data.TodoDatabase
import com.uikylo.smarttodo.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class FilterState(val label: String) {
    ALL("All"),
    ACTIVE("Active"),
    COMPLETED("Done")
}

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    private val _filterState = MutableStateFlow(FilterState.ALL)
    val filterState: StateFlow<FilterState> = _filterState.asStateFlow()

    val todos: StateFlow<List<Todo>>
    val stats: StateFlow<Pair<Int, Int>>

    init {
        val database = TodoDatabase.getDatabase(application)
        repository = TodoRepository(database.todoDao())

        val allTodos = repository.allTodos.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

        todos = combine(allTodos, _filterState) { todoList, filter ->
            when (filter) {
                FilterState.ALL -> todoList
                FilterState.ACTIVE -> todoList.filter { !it.isCompleted }
                FilterState.COMPLETED -> todoList.filter { it.isCompleted }
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        stats = allTodos.map { todoList ->
            val completed = todoList.count { it.isCompleted }
            Pair(completed, todoList.size)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Pair(0, 0))
    }

    fun addTodo(title: String, note: String = "", priority: Priority = Priority.MEDIUM) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.insert(
                Todo(
                    title = title.trim(),
                    note = note.trim(),
                    priority = priority
                )
            )
        }
    }

    fun toggleComplete(todo: Todo) {
        viewModelScope.launch {
            repository.update(
                todo.copy(
                    isCompleted = !todo.isCompleted,
                    completedAt = if (!todo.isCompleted) System.currentTimeMillis() else null
                )
            )
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }

    fun deleteCompleted() {
        viewModelScope.launch {
            repository.deleteCompleted()
        }
    }

    fun setFilter(filter: FilterState) {
        _filterState.value = filter
    }
}
