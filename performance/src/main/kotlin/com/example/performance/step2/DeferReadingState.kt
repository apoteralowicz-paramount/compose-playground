package com.example.performance.step2

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
@Preview
fun ArticlePageWithDeferredRead() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Article(
            modifier = Modifier
                .weight(1.0F)
                .verticalScroll(state = scrollState)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ArticleCompletionWithDeferredRead { scrollState.calculatePercent() }
    }

}

@Composable
@Preview
fun ArticlePageWithoutDeferredRead() {
    val scrollState = rememberScrollState(0)

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Article(
            modifier = Modifier
                .weight(1.0F)
                .verticalScroll(state = scrollState)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ArticleCompletionWithoutDeferredRead(scrollState)
    }

}

@Composable
fun Article(modifier: Modifier) {
    Text(
        fontSize = 16.sp,
        modifier = modifier,
        text = articleText
    )
}

@Composable
fun ArticleCompletionWithoutDeferredRead(scrollState: ScrollState) {
    val percentLeft = remember {
        derivedStateOf {
            scrollState.calculatePercent()
        }
    }
    Box(
        modifier = Modifier.fillMaxWidth().background(Color.LightGray)
    ) {
        // This icon gets redrawn as much as text
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = "${percentLeft.value}% remaining",
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            text = "Next >>",
            fontSize = 18.sp
        )
    }
}

@Composable
fun ArticleCompletionWithDeferredRead(completion: () -> Int) {
    Box(
        modifier = Modifier.fillMaxWidth().background(Color.LightGray),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = "${completion()}% remaining",
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            text = "Next >>",
            fontSize = 18.sp
        )
    }
}

private fun ScrollState.calculatePercent(): Int {
    val remaining = (maxValue.toFloat() - value.toFloat())
    return (remaining / maxValue * 100).roundToInt()
}


