package com.muzafferatmaca.androidtvcompose.presentation.screens.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:35
 */
@Composable
fun CatalogBrowser(
    modifier: Modifier = Modifier,
    catalogBrowserViewModel: CatalogBrowserViewModel = hiltViewModel(),
    onMovieSelected: (Movie) -> Unit = {}
) {
}
