package com.example.performance.step5

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.performance.step4.TodoItem
import com.example.performance.testapp.TodoEvent
import com.example.performance.testapp.TodoItemState
import com.example.performance.testapp.mockData

/**
 * Compose cannot compare and identify anonymous functions with parameters
 * The workaround is to remember the lambda or to use delegation (:: operator)
 * However, if the parameter is unstable, lambda will continue to cause recompositions
 */

@Composable
@Preview
fun UnstableLambda() {
    val list = remember { mockData.toMutableStateList() }

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
    )
    {
        items(items = list, key = { it.id }) { item ->
            TodoItem(state = item, onEvent = { event ->
                // This lambda is unstable
                // However, it will not be marked in compose performance reports as such

                when (event) {
                    is TodoEvent.Toggle -> {
                        list[event.index] = item.copy(checked = !item.checked)
                    }

                    is TodoEvent.Delete -> {
                        list.removeAt(event.index)
                    }
                }
            })
        }
    }
}

@Composable
@Preview
fun StableLambda() {
    val list = remember { mockData.toMutableStateList() }

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
    )
    {
        items(items = list, key = { it.id }) { item ->
            TodoItem(state = item, onEvent = handleEvent(list = list))
        }
    }
}

@Composable
private fun handleEvent(list: SnapshotStateList<TodoItemState>): (TodoEvent) -> Unit =
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