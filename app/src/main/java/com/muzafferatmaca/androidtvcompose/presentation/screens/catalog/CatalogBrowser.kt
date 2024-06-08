package com.muzafferatmaca.androidtvcompose.presentation.screens.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import com.muzafferatmaca.androidtvcompose.domain.entity.Movie
import com.muzafferatmaca.androidtvcompose.presentation.components.MovieCard

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:35
 */
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
        items(categoryList) { category ->
            Text(text = category.name)
            TvLazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(category.movieList) {
                    MovieCard(movie = it, onClick = { onMovieSelected(it) })
                }
            }
        }
    }
}
