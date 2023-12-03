package com.example.performance.testapp

import kotlinx.coroutines.flow.MutableStateFlow

class TodoViewModel {
    private val state = MutableStateFlow<TodoListState>(TodoListState.Init)

    init {
        state.value = TodoListState.Result(mockData)
    }

    fun onEvent(todoEvent: TodoEvent) = reduce(state.value, todoEvent)

    private fun reduce(oldState: TodoListState, todoEvent: TodoEvent) {
        when (todoEvent) {
            is TodoEvent.Toggle -> {
                if (oldState is TodoListState.Result) {
                    val item = oldState.todoList[todoEvent.index]
                    state.value = oldState.copy(
                        todoList = oldState.todoList.toMutableList()
                            .also { it[item.id] = item.copy(checked = !item.checked) }
                    )
                }
            }
            else -> {}
        }
    }
}