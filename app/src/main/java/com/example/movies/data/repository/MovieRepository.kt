package com.example.movies.data.repository

import com.example.movies.common.network.SafeApiRequest
import com.example.movies.data.db.room.AppDatabase
import com.example.movies.data.model.Movie
import com.example.movies.data.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val moviesApi: MoviesApi
) : SafeApiRequest() {

    private val movieDao by lazy {
        appDatabase.getMovieDao()
    }


    private fun List<Movie>.mapToDb(): List<Movie> {
        return this.map {
            Movie(
                it.id ?: -1,
                it.url ?: "",
                it.name ?: "",
                it.season ?: 0,
                it.number ?: 0,
                it.type ?: "",
                it.airDate ?: "",
                it.airTime ?: "",
                it.airStamp ?: "",
                it.runTime ?: 0,
                it.rating?.average ?: 0F,
                it.image?.medium ?: "",
                it.image?.original ?: "",
                it.summary ?: ""
            )
        }
    }

    suspend fun getMovies(): List<Movie>? {
        return withContext(Dispatchers.IO) {
            val movies = movieDao.getMovies()
            movies.ifEmpty {
                val response = safeApiRequest { moviesApi.getMovies() }
                if (response.isNotEmpty()) {
                    response.mapToDb().let {
                        movieDao.saveMovies(it)
                        it
                    }
                } else null
            }
        }
    }
}