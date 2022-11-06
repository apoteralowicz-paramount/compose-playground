package com.example.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SnappingRow(itemCount: Int = 30) {

    val state = rememberLazyListState()

    LazyRow(
        state = state,
        flingBehavior = rememberSnapFlingBehavior(state)
    ) {
        items(itemCount) { index ->
            RandomShape(
                modifier = Modifier.padding(
                    end = if (index == itemCount - 1) 0.dp else 10.dp
                ),
                size = 100.dp
            )
        }
    }
}

@Preview
@Composable
fun SnappingRowPreview() {
    SnappingRow()
}
