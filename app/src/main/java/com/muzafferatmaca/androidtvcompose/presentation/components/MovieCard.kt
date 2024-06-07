package com.muzafferatmaca.androidtvcompose.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.muzafferatmaca.androidtvcompose.R
import com.muzafferatmaca.androidtvcompose.domain.entity.Movie
import com.muzafferatmaca.androidtvcompose.presentation.theme.AndroidTvComposeTheme

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:04
 */
@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (Movie) -> Unit = {}
) {
    Card(
        onClick = { onClick(movie) },
        modifier = modifier
            .widthIn(320.dp)
            .aspectRatio(16f / 9f)
    ) {
        AsyncImage(
            model = movie.cardImageUrl,
            contentDescription = movie.description,
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TV_1080p,
    backgroundColor = 0xFF00FF00
)
@Composable
private fun MovieCardPreview() {
    AndroidTvComposeTheme {
        MovieCard(modifier = Modifier, movie = Movie(), onClick = {})
    }
}