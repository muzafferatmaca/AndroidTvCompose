package com.muzafferatmaca.androidtvcompose.di

import com.muzafferatmaca.androidtvcompose.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:42
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMovieRepository(): MovieRepository {
        return MovieRepository()
    }

}