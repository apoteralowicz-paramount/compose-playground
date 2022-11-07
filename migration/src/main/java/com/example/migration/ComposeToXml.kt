package com.example.migration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.layouts.PageOne
import com.example.migration.databinding.LayoutWithComposeBinding

class ComposeToXmlActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = LayoutWithComposeBinding.inflate(layoutInflater)

        binding.composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PageOne()
            }
        }

        val view = binding.root
        setContentView(view)
    }
}