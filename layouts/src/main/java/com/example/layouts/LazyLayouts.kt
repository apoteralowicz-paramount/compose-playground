package com.example.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


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
                modifier = Modifier
                    .padding(
                        end = if (index == itemCount - 1) 0.dp else 10.dp
                    )
                    .size(100.dp)
            )
        }
    }
}

@Composable
fun MultiSpanGrid(modifier: Modifier = Modifier, itemCount: Int = 30) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
    ) {
        items(
            count = itemCount,
            span = { index ->
                GridItemSpan(
                    if (index % 5 == 0) 2 else 1
                )
            },
            itemContent = { index ->
                ShapeCard(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(50.dp),
                    index = index
                )
            }
        )
    }
}

@Preview
@Composable
fun SnappingRowPreview() {
    SnappingRow()
}

@Preview(widthDp = 400, heightDp = 800)
@Composable
fun MultiSpanGridSmallScreenPreview() {
    MultiSpanGrid()
}
