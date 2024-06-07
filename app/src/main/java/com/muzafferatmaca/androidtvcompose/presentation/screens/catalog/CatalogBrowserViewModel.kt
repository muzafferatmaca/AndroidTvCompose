package com.muzafferatmaca.androidtvcompose.presentation.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzafferatmaca.androidtvcompose.data.repository.MovieRepository
import com.muzafferatmaca.androidtvcompose.domain.entity.Category
import com.muzafferatmaca.androidtvcompose.domain.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:35
 */
@HiltViewModel
class CatalogBrowserViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val featuredMovieList: StateFlow<List<Movie>> = flow {
        emit(movieRepository.getFeaturedMovieList())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    val categoryList: StateFlow<List<Category>> = flow {
        var list = listOf<Category>()
        movieRepository.getCategoryList().forEach {
            val category = Category(
                name = it,
                movieList = movieRepository.getMovieListByCategory(it)
            )
            list = list + listOf(category)
            emit(list)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())
}


