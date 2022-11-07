package com.example.migration

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.migration.databinding.LayoutWithComposeBinding

@Composable
@Preview
fun LoadXml() {
    AndroidView(
        factory = { context ->
            val binding = LayoutWithComposeBinding.inflate(LayoutInflater.from(context))
            binding.root
        },
    )
}
