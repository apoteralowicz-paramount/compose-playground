package com.example.layouts

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class AnimationState {
    Big, Small
}

@Preview
@Composable
fun SynchronizedAnimation() {
    var animationState by remember { mutableStateOf(AnimationState.Small) }

    val transition = updateTransition(targetState = animationState, label = "testTransition")

    val scale by transition.animateFloat(
        transitionSpec = {
            tween(500)
        },
        label = ""
    ) { state ->
        when (state) {
            AnimationState.Big -> 1.0f
            AnimationState.Small -> 0.5f
        }
    }

    Row {
        RandomShape(modifier = Modifier
            .size(100.dp)
            .scale(scale = scale)
            .clickable {
                val newState =
                    if (animationState == AnimationState.Big) AnimationState.Small else AnimationState.Big
                animationState = newState
            }
        )
    }
}