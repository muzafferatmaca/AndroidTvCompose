package com.muzafferatmaca.androidtvcompose.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.muzafferatmaca.androidtvcompose.domain.entity.Movie

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:37
 */
@Composable
fun Details(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(
                vertical = 32.dp,
                horizontal = 48.dp
            )
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.cardImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.background,
                                Color.Transparent
                            )
                        )
                    )
                    .fillMaxSize()
            ){
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 48.dp, vertical = 24.dp)
                ) {
                    Text(text = movie.title, style = MaterialTheme.typography.displayMedium)
                    Text(text = movie.studio, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = movie.description)
                }
            }

        }
    }
}
