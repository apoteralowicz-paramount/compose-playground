package com.example.performance.testapp

import androidx.compose.runtime.Immutable

sealed interface TodoListState {

    @Immutable
    object Init : TodoListState

    @Immutable
    data class Result(val todoList: List<TodoItemState>) : TodoListState
}
