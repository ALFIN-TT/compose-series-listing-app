package com.example.movies.data.network

import com.example.movies.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("/shows/82/episodes")
    suspend fun getMovies(): Response<List<Movie>>

}