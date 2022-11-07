package com.example.layouts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun Tags() =
    FlowRow(
        mainAxisSpacing = 4.dp,
        crossAxisSpacing = 2.dp
    ) {
        repeat(20) { index ->
            Tag(text = "Tag$index")
        }
    }


@Composable
fun Tag(modifier: Modifier = Modifier, text: String) {

    val borderColor = rememberSaveable(saver = colorSaver()) { randomLightColor() }

    Box(
        modifier = modifier
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview
@Composable
fun FlowRowExample() {
    Tags()
}

@Preview(widthDp = 600)
@Composable
fun FlowRowWideExample() {
    Tags()
}