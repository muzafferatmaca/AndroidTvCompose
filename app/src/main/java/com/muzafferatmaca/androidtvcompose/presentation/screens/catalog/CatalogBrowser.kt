package com.muzafferatmaca.androidtvcompose.presentation.screens.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import coil.compose.AsyncImage
import com.muzafferatmaca.androidtvcompose.R
import com.muzafferatmaca.androidtvcompose.domain.entity.Movie
import com.muzafferatmaca.androidtvcompose.presentation.components.MovieCard

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:35
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CatalogBrowser(
    modifier: Modifier = Modifier,
    catalogBrowserViewModel: CatalogBrowserViewModel = hiltViewModel(),
    onMovieSelected: (Movie) -> Unit = {}
) {
    val categoryList by catalogBrowserViewModel.categoryList.collectAsStateWithLifecycle()

    TvLazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 48.dp, vertical = 32.dp)
    ) {
        item {
            val featuredMovieList by catalogBrowserViewModel.featuredMovieList.collectAsStateWithLifecycle()
            Carousel(
                itemCount = featuredMovieList.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(376.dp)
            ) { indexOfCarouselSlide ->
                val featuredMovie = featuredMovieList[indexOfCarouselSlide]
                val backGroundColor = MaterialTheme.colorScheme.background
                Box {
                    AsyncImage(
                        model = featuredMovie.backgroundImageUrl,
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        contentAlignment = Alignment.BottomStart,
                        modifier = Modifier
                            .fillMaxSize()
                            .drawBehind {
                                val brush = Brush.horizontalGradient(
                                    listOf(backGroundColor, Color.Transparent)
                                )
                                drawRect(brush)
                            }
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(text = featuredMovie.title)
                            Spacer(modifier = Modifier.height(28.dp))
                            Button(onClick = { onMovieSelected(featuredMovie) }) {
                                Text(text = stringResource(id = R.string.show_details))
                            }
                        }
                    }
                }
            }
        }
        items(categoryList) { category ->
            Text(text = category.name)
            TvLazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(category.movieList) {
                    MovieCard(
                        movie = it,
                        onClick = { onMovieSelected(it) }
                    )
                }
            }
        }
    }
}
