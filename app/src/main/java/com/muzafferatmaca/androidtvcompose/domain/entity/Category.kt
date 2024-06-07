package com.muzafferatmaca.androidtvcompose.domain.entity

import androidx.compose.runtime.Stable

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:36
 */
@Stable
data class Category(val name: String, val movieList: List<Movie>)