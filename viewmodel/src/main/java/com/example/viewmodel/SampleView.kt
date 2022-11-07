package com.example.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.Coil
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.CachePolicy
import com.example.viewmodel.SampleState.*

@Composable
fun SampleWrapper(viewModel: SampleViewModel = viewModel()) {
    val state = viewModel.state.collectAsState()
    DisableImageCache()
    SampleView(
        state = state,
        onButtonClick = { viewModel.loadNextImage() }
    )
}

@Composable
fun DisableImageCache() {
    val context = LocalContext.current

    LaunchedEffect(Coil) {
        val imageLoader = context.imageLoader.newBuilder()
            .diskCachePolicy(CachePolicy.DISABLED)
            .memoryCachePolicy(CachePolicy.DISABLED)
            .build()
        Coil.setImageLoader(imageLoader)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SampleView(
    state: State<SampleState>,
    onButtonClick: () -> Unit,
) = AnimatedContent(state) {
    when (val currentState = state.value) {
        Error -> LoadingError(onRetryClick = onButtonClick)
        is Result -> RandomImage(currentState.imageUrl, onButtonClick)
        Loading -> LoadingIndicator()
    }
}

@Composable
fun RandomImage(url: String, onButtonClick: () -> Unit, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            painter = rememberAsyncImagePainter(url, contentScale = ContentScale.Inside),
            contentDescription = null
        )
        Button(onClick = onButtonClick) {
            Text("Next")
        }
    }
}

@Composable
fun LoadingError(modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ooops! Something went wrong!")
        Button(onClick = onRetryClick) {
            Text("Retry")
        }
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun PreviewResult() {
    val state: State<SampleState> = mutableStateOf(
        Result(
            imageUrl = "https://picsum.photos/200/300",
            refreshCount = 1
        ),
    )
    SampleView(
        state = state,
        onButtonClick = {}
    )
}