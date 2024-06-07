package com.muzafferatmaca.androidtvcompose.data.repository

import com.muzafferatmaca.androidtvcompose.data.source.MovieAPI
import com.muzafferatmaca.androidtvcompose.data.source.MovieAPI.loadCategoryList
import com.muzafferatmaca.androidtvcompose.domain.entity.Movie
import com.muzafferatmaca.androidtvcompose.util.unwrapOr
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:00
 */
class MovieRepository(
    private val dataSource: MovieAPI = MovieAPI,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * In-memory caches.
     */
    private val categoryMovieListMap: MutableMap<String, List<Movie>> = mutableMapOf()
    private val idMovieMap: MutableMap<Long, Movie?> = mutableMapOf()
    private var categoryList: List<String> = listOf()

    /*
     Mutexes for in-memory caches.
     Please refer to https://developer.android.com/topic/architecture/data-layer#in-memory-cache for details
     */
    private val categoryMovieListMapMutex = Mutex()
    private val idMovieMapMutex = Mutex()
    private val categoryListMutex = Mutex()

    suspend fun getCategoryList(): List<String> {
        if (categoryList.isEmpty()) {
            categoryListMutex.withLock {
                categoryList = loadCategoryList().unwrapOr(categoryList)
            }
        }
        prefetchMovieListByCategory(category = categoryList[0])
        return categoryListMutex.withLock { categoryList }
    }

    suspend fun getFeaturedMovieList(): List<Movie> {
        val movieList = MovieAPI.loadFeaturedMovieList().unwrapOr(listOf())
        updateCache(movieList)
        return movieList
    }

    suspend fun findMovieById(id: Long): Movie? {
        idMovieMapMutex.withLock {
            if (!idMovieMap.contains(id)) {
                idMovieMap[id] = MovieAPI.findMovieById(id).unwrapOr(null)
            }
        }
        return idMovieMapMutex.withLock { idMovieMap[id] }
    }

    suspend fun getMovieListByCategory(category: String): List<Movie> {
        loadMovieListByCategory(category)
        prefetchNeighborCategories(category = category)
        return categoryMovieListMapMutex.withLock { categoryMovieListMap[category]!! }
    }

    private suspend fun loadMovieListByCategory(category: String, force: Boolean = false) {
        if (force || !categoryMovieListMap.contains(category)) {
            updateCache(
                category = category,
                movieList = MovieAPI.getMovieListByCategory(category).unwrapOr(listOf())
            )
        }
    }

    private suspend fun updateCache(category: String, movieList: List<Movie>) {
        categoryMovieListMapMutex.withLock {
            categoryMovieListMap[category] = movieList
        }
        updateCache(movieList)
    }

    private suspend fun updateCache(movieList: List<Movie>) {
        idMovieMapMutex.withLock {
            movieList.forEach {
                idMovieMap[it.id] = it
            }
        }
    }

    private fun prefetchMovieListByCategory(category: String) {
        CoroutineScope(dispatcher).launch {
            loadMovieListByCategory(category)
        }
    }

    private fun prefetchNeighborCategories(
        category: String,
        neighborCount: Int = 1,
    ) {
        val indexForCategory = Integer.max(categoryList.indexOf(category), 0)
        val neighbors =
            (
                    indexForCategory..Integer.min(
                        indexForCategory + neighborCount,
                        categoryList.size - 1
                    )
                    ).map { categoryList[it] }
        CoroutineScope(dispatcher).launch {
            neighbors.forEach { loadMovieListByCategory(it) }
        }
    }
}
