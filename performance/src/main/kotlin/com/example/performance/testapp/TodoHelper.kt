package com.example.performance.testapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList

val mockData =
    List(300) { index ->
        TodoItemState(
            index,
            false,
            "Todo$index"
        )
    }

@Composable
internal fun handleEvent(list: SnapshotStateList<TodoItemState>): (TodoEvent) -> Unit =
    { event ->
        when (event) {
            is TodoEvent.Toggle -> {
                val item = list[event.index]
                list[event.index] = item.copy(checked = !item.checked)
            }

            is TodoEvent.Delete -> {
                list.removeAt(event.index)
            }
        }
    }