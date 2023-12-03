@file:OptIn(ExperimentalFoundationApi::class)

package com.example.performance.step4

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.performance.testapp.TodoEvent
import com.example.performance.testapp.TodoItemState
import com.example.performance.testapp.mockData

/**
 *
 */
@Composable
fun TodoListNoKeys(list: List<TodoItemState>, onEvent: (TodoEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
    ) {
        items(items = list) { item ->
            TodoItem(state = item, onEvent = onEvent)
        }
    }
}


@Composable
@Preview
fun TodoListKeysPreview() {
    val list = remember { mockData.toMutableStateList() }

    TodoListWithKeys(list = list, onEvent = handleEvent(list))
}

@Composable
@Preview
fun TodoListNoKeysPreview() {
    val list = remember { mockData.toMutableStateList() }

    TodoListNoKeys(list = list, onEvent = handleEvent(list))
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

@Composable
fun TodoListWithKeys(list: List<TodoItemState>, onEvent: (TodoEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
    ) {
        items(items = list, key = { it.id }) { item ->
            TodoItem(state = item, onEvent = onEvent)
        }
    }
}

@Composable
fun TodoItem(state: TodoItemState, onEvent: (TodoEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = { onEvent(TodoEvent.Delete(state.id)) },
                onClick = {}
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = state.text)
        Checkbox(
            checked = state.checked,
            onCheckedChange = { onEvent(TodoEvent.Toggle(state.id)) })
    }
}