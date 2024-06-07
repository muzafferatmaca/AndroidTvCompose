package com.muzafferatmaca.androidtvcompose.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzafferatmaca.androidtvcompose.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:38
 */
@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val movieFlow = savedStateHandle.getStateFlow<Long?>("id", null).map {
        if (it != null) {
            movieRepository.findMovieById(id = it)
        } else {
            null
        }
    }

    val detailsLoadingState = movieFlow.map { movie ->
        if (movie != null) {
            DetailsLoadingState.Ready(movie = movie)
        } else {
            DetailsLoadingState.NotFound
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DetailsLoadingState.Loading
    )
}
