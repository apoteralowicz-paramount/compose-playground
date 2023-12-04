package com.example.performance.testapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.metrics.performance.PerformanceMetricsState
import com.example.performance.step4.TodoItem
import com.example.performance.step4.TodoListWithKeys
import dagger.hilt.android.AndroidEntryPoint

/**
 * JankStats sample:
 * https://github.com/android/performance-samples/tree/main/JankStatsSample/app/src/main/java/com/example/jankstats/compose
 */
@AndroidEntryPoint
class TodoComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TodoViewModel = viewModel()
            val state by viewModel.state.collectAsState()

            (state as? TodoListState.Result)?.let {
                TrackedTodoList(list = it.todoList) {
                    viewModel.onEvent(it)
                }
            }
        }
    }

    @Composable
    fun TrackedTodoList(list: List<TodoItemState>, onEvent: (TodoEvent) -> Unit) {

        val metricsStateHolder = rememberMetricsStateHolder()
        val listState = rememberLazyListState()

        // Reporting scrolling state from compose should be done from side effect to prevent recomposition.
        LaunchedEffect(metricsStateHolder, listState) {
            snapshotFlow { listState.isScrollInProgress }.collect { isScrolling ->
                if (isScrolling) {
                    metricsStateHolder.state?.putState("LazyList", "Scrolling")
                } else {
                    metricsStateHolder.state?.removeState("LazyList")
                }
            }
        }

        TodoListWithKeys(list = list, state = listState, onEvent = onEvent)
    }

    @Composable
    fun rememberMetricsStateHolder(): PerformanceMetricsState.Holder {
        val view = LocalView.current
        return remember(view) { PerformanceMetricsState.getHolderForHierarchy(view) }
    }
}