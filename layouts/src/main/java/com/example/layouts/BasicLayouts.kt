package com.example.layouts

import android.widget.GridLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun RowExample() =
    Row {
        repeat(10) { RandomShape() }
    }

@Composable
fun ColumnExample() =
    Column() {
        repeat(10) { RandomShape() }
    }

@Preview
@Composable
fun RowPreview() {
    RowExample()
}

@Preview
@Composable
fun ColumnPreview() {
    ColumnExample()
}
