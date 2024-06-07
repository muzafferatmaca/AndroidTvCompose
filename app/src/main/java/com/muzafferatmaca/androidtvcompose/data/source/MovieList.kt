package com.muzafferatmaca.androidtvcompose.data.source

import com.muzafferatmaca.androidtvcompose.domain.entity.Movie

/**
 * Created by Muzaffer Atmaca on 7.06.2024 at 23:59
 */
internal object MovieList {
    val categories = listOf(
        "Category Zero",
        "Category One",
        "Category Two",
        "Category Three",
        "Category Four",
        "Category Five"
    )

    private val all: List<Movie> by lazy {
        setupMovies()
    }

    val featured by lazy {
        (all + all).shuffled()
    }

    private val categoryMoviesMap: Map<String, List<Movie>> by lazy {
        categories.associateWith { all.shuffled() }
    }

    fun getByCategory(category: String): List<Movie> {
        return categoryMoviesMap[category] ?: all
    }

    fun findById(id: Long): Movie? {
        return all.find { id == it.id }
    }

    private fun setupMovies(): List<Movie> {
        var count: Long = 0

        val title = arrayOf(
            "Zeitgeist 2010_ Year in Review",
            "Google Demo Slam_ 20ft Search",
            "Introducing Gmail Blue",
            "Introducing Google Fiber to the Pole",
            "Introducing Google Nose"
        )

        val description = "Fusce id nisi turpis. Praesent viverra bibendum semper. " +
                "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa," +
                "non congue tellus est quis tellus." +
                "Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit " +
                "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. " +
                "Nulla eget dolor in elit facilisis mattis. " +
                "Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id " +
                "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat."
        val studio = arrayOf(
            "Studio Zero",
            "Studio One",
            "Studio Two",
            "Studio Three",
            "Studio Four"
        )

        val baseURL = "https://commondatastorage.googleapis.com/android-tv/Sample%20videos"

        val videoUrl = arrayOf(
            "$baseURL/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
            "$baseURL/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
            "$baseURL/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
            "$baseURL/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
            "$baseURL/April%20Fool's%202013/Introducing%20Google%20Nose.mp4"
        )
        val bgImageUrl = arrayOf(
            "$baseURL/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/bg.jpg",
            "$baseURL/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg",
            "$baseURL/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg",
            "$baseURL/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg",
            "$baseURL/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg"
        )
        val cardImageUrl = arrayOf(
            "$baseURL/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg",
            "$baseURL/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg",
            "$baseURL/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg",
            "$baseURL/April%20Fool's%202013/" +
                    "Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg",
            "$baseURL/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg"
        )

        val list = title.indices.map {
            Movie(
                id = count++,
                title = title[it],
                description = description,
                studio = studio[it],
                videoUrl = videoUrl[it],
                cardImageUrl = cardImageUrl[it],
                backgroundImageUrl = bgImageUrl[it],
            )
        }

        return list
    }
}
