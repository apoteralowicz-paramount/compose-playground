package com.example.performance.testapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.performance.step4.TodoListWithKeys
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TodoComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TodoViewModel = viewModel()
            val state by viewModel.state.collectAsState()

            (state as? TodoListState.Result)?.let {
                TodoListWithKeys(list = it.todoList) {
                    viewModel.onEvent(it)
                }
            }
        }
    }
}