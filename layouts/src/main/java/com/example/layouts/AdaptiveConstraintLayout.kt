package com.example.layouts

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

object LayoutIds {
    val image = "image"
    val grid = "grid"
}

fun bigScreenConstraints() =
    ConstraintSet {
        val image = createRefFor(LayoutIds.image)
        val grid = createRefFor(LayoutIds.grid)
        val guideline = createGuidelineFromStart(0.3f)

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(guideline, margin = 10.dp)
            bottom.linkTo(parent.bottom)
        }

        constrain(grid) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(guideline)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
    }

fun smallScreenConstraints() =
    ConstraintSet {
        val image = createRefFor(LayoutIds.image)
        val grid = createRefFor(LayoutIds.grid)

        constrain(image) {
            top.linkTo(parent.top)
            bottom.linkTo(grid.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.fillToConstraints
        }

        constrain(grid) {
            top.linkTo(image.bottom)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        }
    }


@Composable
fun ImageAndGrid() {
    val configuration = LocalConfiguration.current

    val constraints =
        if (configuration.screenWidthDp.dp >= 600.dp)
            bigScreenConstraints()
        else
            smallScreenConstraints()

    ConstraintLayout(constraints) {
        PlaceholderImage(modifier = Modifier.layoutId(LayoutIds.image))
        MultiSpanGrid(modifier = Modifier.layoutId(LayoutIds.grid))
    }
}

@Composable
fun PlaceholderImage(modifier: Modifier) = Image(
    modifier = modifier,
    painter = painterResource(id = android.R.drawable.ic_menu_camera),
    contentDescription = null
)

@Preview(widthDp = 400, heightDp = 400)
@Composable
fun ImageAndGridSmallPreview() {
    ImageAndGrid()
}

@Preview(widthDp = 1200, heightDp = 600)
@Composable
fun ImageAndGridBigPreview() {
    ImageAndGrid()
}