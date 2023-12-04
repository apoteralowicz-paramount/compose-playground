package com.example.performance.testapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<TodoListState>(TodoListState.Init)
    val state: StateFlow<TodoListState> = _state

    init {
        _state.value = TodoListState.Result(mockData)
    }

    fun onEvent(todoEvent: TodoEvent) = reduce(_state.value, todoEvent)

    private fun reduce(oldState: TodoListState, todoEvent: TodoEvent) {
        when (todoEvent) {
            is TodoEvent.Toggle -> {
                if (oldState is TodoListState.Result) {
                    val item = oldState.todoList[todoEvent.index]
                    _state.value = oldState.copy(
                        todoList = oldState.todoList.toMutableList()
                            .also { it[item.id] = item.copy(checked = !item.checked) }
                    )
                }
            }

            else -> {}
        }
    }
}