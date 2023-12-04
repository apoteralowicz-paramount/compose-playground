package com.example.performance.step3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun DerivedStateExample() {
    val counter = remember { mutableIntStateOf(0) }
    val color = remember {
        derivedStateOf {
            if (counter.intValue > 5) Color.Red else Color.Blue
        }
    }

    LikeButton(likes = counter.intValue) { counter.intValue += 1 }
}

@Composable
@Preview
fun NoDerivedStateExample() {
    var counter by remember { mutableIntStateOf(0) }

    LikeButton(likes = counter) { counter += 1 }
}

@Composable
private fun LikeButton(
    likes: Int,
    onClick: () -> Unit
) {
    val color = if (likes > 5) Color.Red else Color.Blue

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(color = color, shape = RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Text("Like", color = Color.White)
    }
}

@Composable
private fun LikeButtonWithDerivedState(
    likes: Int,
    onClick: () -> Unit
) {
    val color = remember { derivedStateOf { if (likes > 5) Color.Red else Color.Blue } }

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(color = color.value, shape = RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Text("Like", color = Color.White)
    }
}