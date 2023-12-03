package com.example.performance.testapp

sealed interface TodoEvent {

    class Toggle(val index: Int): TodoEvent
    class Delete(val index: Int): TodoEvent
}