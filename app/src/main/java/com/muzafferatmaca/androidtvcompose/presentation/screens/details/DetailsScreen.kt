package com.muzafferatmaca.androidtvcompose.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:38
 */
@Composable
fun DetailsScreen(
    backAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state by detailsScreenViewModel.detailsLoadingState.collectAsStateWithLifecycle()

    when (val s = state) {
        is DetailsLoadingState.Ready -> Details(
            movie = s.movie,
            modifier = modifier
        )

        is DetailsLoadingState.NotFound -> NotFound(
            backAction = backAction,
            modifier = modifier.fillMaxSize()
        )

        else -> Loading(modifier = modifier.fillMaxSize())
    }
}

/**
 * Composable for DetailsLoadingState.Loading state.
 */
@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "Loading...", style = MaterialTheme.typography.displayMedium)
    }
}


@Composable
private fun NotFound(backAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(text = "Not found", style = MaterialTheme.typography.displayMedium)
            Button(onClick = backAction) {
                Text(text = "Back to the previous screen")
            }
        }
    }
}