package com.example.movies.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.data.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(showInfo: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(showInfo: List<Movie>)

    @Query("SELECT * FROM movies_tb")
    fun getMovies(): List<Movie>
}