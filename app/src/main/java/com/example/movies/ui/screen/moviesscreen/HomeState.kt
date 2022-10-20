package com.example.movies.ui.screen.moviesscreen

import com.example.movies.data.model.Movie

data class HomeState(
    var isLoading: Boolean = false,
    var data: List<Movie>? = null,
    var error: String = ""
)
