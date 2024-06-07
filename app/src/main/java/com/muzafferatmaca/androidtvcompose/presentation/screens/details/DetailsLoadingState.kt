package com.muzafferatmaca.androidtvcompose.presentation.screens.details

import com.muzafferatmaca.androidtvcompose.domain.entity.Movie

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:38
 */
sealed interface DetailsLoadingState {
    // The screen is in this state when it is loading a movie object via repository.
    data object Loading : DetailsLoadingState

    // The screen is in this state when it can not find a movie object associated with the given ID.
    data object NotFound : DetailsLoadingState

    // The screen is in this state when it become ready to display the movie info.
    class Ready(val movie: Movie) : DetailsLoadingState
}