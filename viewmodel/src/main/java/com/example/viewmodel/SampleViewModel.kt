package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

private val sampleUrl = "https://picsum.photos/600/600"
private val sampleDescription = "lorem ipsum"

@HiltViewModel
class SampleViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<SampleState>(SampleState.Loading)
    val state: StateFlow<SampleState> = _state

    private var imageCount = 0

    init {
        loadNextImage()
    }

    // simulate fetching
    fun loadNextImage() =
        viewModelScope.launch {
            _state.value = SampleState.Loading
            delay(2000)

            val error = Random.nextBoolean()

            _state.value =
                if (error) SampleState.Error
                else {
                    imageCount += 1
                    SampleState.Result(sampleUrl, imageCount)
                }
        }
}