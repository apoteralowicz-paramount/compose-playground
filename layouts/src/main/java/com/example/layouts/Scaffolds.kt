package com.example.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageTemplate(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable (PaddingValues) -> Unit,
) = Scaffold(
    modifier = modifier,
    topBar = { TopBar(title = title) },
    bottomBar = { BottomBar() },
    content = content
)

@Composable
private fun BottomBar() =
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_compass),
                contentDescription = null
            )
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_camera),
                contentDescription = null
            )
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_zoom),
                contentDescription = null
            )
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
fun PageOne() = PageTemplate(title = "Page One") {
    MultiSpanGrid(modifier = Modifier.padding(it))
}

@Preview
@Composable
fun PageTwo() = PageTemplate(title = "PageTwo") {
    Tags(modifier = Modifier.padding(it))
}
