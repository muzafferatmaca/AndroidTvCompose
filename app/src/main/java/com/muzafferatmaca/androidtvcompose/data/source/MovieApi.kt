package com.muzafferatmaca.androidtvcompose.data.source

import com.muzafferatmaca.androidtvcompose.domain.entity.Movie
import com.muzafferatmaca.androidtvcompose.util.Option
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:02
 */


/**
 * MovieAPI object simulates API calls.
 */
object MovieAPI {

    suspend fun loadCategoryList(): Option<List<String>> {
        return withDelay {
            Option.Some(MovieList.categories)
        }
    }

    suspend fun loadFeaturedMovieList(): Option<List<Movie>> {
        return withDelay {
            Option.Some(MovieList.featured)
        }
    }

    suspend fun getMovieListByCategory(category: String): Option<List<Movie>> {
        return withDelay {
            Option.Some(MovieList.getByCategory(category))
        }
    }

    suspend fun findMovieById(id: Long): Option<Movie> {
        return withDelay {
            val movie = MovieList.findById(id)
            if (movie == null) {
                Option.None
            } else {
                Option.Some(movie)
            }
        }
    }
}

private suspend fun<T> withDelay(
    delayInMilSec: Long = Random.nextLong(300, 1500),
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    task: suspend () -> T,
): T {
    return withContext(context = coroutineDispatcher) {
        delay(delayInMilSec)
        task()
    }
}