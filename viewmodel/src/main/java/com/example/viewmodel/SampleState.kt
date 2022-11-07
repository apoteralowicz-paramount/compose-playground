package com.example.viewmodel

sealed class SampleState {
    object Loading : SampleState()
    object Error : SampleState()
    data class Result(val imageUrl: String, val refreshCount: Int) : SampleState()
}