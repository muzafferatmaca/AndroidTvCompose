package com.muzafferatmaca.androidtvcompose.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.tv.material3.Surface
import com.muzafferatmaca.androidtvcompose.presentation.navigaiton.NavHost
import com.muzafferatmaca.androidtvcompose.presentation.theme.AndroidTvComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTvComposeTheme {
                Surface(
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavHost()
                }
            }
        }
    }
}

