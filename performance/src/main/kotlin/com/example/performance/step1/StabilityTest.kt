package com.example.performance.step1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

/**
 * Run ./gradlew performance:assembleRelease -PcomposeCompilerReports=true
 * Reports are generated in build/compose_compiler directory
 */
@Composable
fun UnstableDataComposable(data: UnstableData) {
    Text(text = data.list[0])
}

@Composable
fun ForcedImmutableDataComposable(data: ForceImmutableData) {
    Text(text = data.list[0])
}

@Composable
fun ForcedStableDataComposable(data: ForcedStableData) {
    val text = data.text.collectAsState()
    Text(text = text.value)
}

@Composable
fun UnmarkedStableDataComposable(data: UnmarkedStableData) {
    val text = data.text.collectAsState()
    Text(text = text.value)
}
