package com.example.benchmark

import android.graphics.Point
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.uiautomator.By
import org.junit.Rule
import org.junit.Test

class ExampleScrollBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun scroll() = benchmarkRule.measureRepeated(
        packageName = "com.example.performance",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        startActivityAndWait()

        val list = device.findObject(By.scrollable(true))

        repeat(3) {
            list.drag(
                Point(0, list.visibleCenter.y / 3)
            )
        }
    }
}