package com.example.movies.ui.screen.navigation

sealed class Screen( val route: String) {
    object MovieScreen : Screen("movie_screen")
}