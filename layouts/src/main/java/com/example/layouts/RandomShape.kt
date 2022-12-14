package com.example.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.min
import kotlin.random.Random

private val shapes = listOf<Shape>(
    RoundedCornerShape(percent = 20),
    CircleShape,
    CutCornerShape(percent = 50),
)

@Composable
fun RandomShape(
    modifier: Modifier = Modifier,
    minSize: Dp = 20.dp
) {

    val color = rememberSaveable(saver = colorSaver()) { randomLightColor() }
    val shape = rememberSaveable(saver = shapeSaver()) { shapes.random() }

    Box(
        modifier = modifier
            .defaultMinSize(minSize, minSize)
            .background(color = color, shape = shape)
    )
}

@Composable
fun ShapeCard(modifier: Modifier = Modifier, index: Int) =
    Row(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(percent = 10)),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RandomShape()
        Text("Item no $index")
    }

fun randomLightColor(): Color {
    val r = Random.nextInt(128) + 127
    val g = Random.nextInt(128) + 127
    val b = Random.nextInt(128) + 127

    return Color(r, g, b)
}

fun colorSaver() = Saver<Color, Long>(
    save = { color -> color.value.toLong() },
    restore = { value -> Color(value.toULong()) }
)

fun shapeSaver() = Saver<Shape, Int>(
    save = { shape -> shapes.indexOf(shape) },
    restore = { index -> shapes[index] }
)