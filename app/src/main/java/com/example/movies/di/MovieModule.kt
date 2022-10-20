package com.example.movies.di

import android.content.Context
import com.example.movies.data.db.room.AppDatabase
import com.example.movies.data.network.BaseApi
import com.example.movies.data.network.MoviesApi
import com.example.movies.data.repository.MovieRepository
import com.example.movies.data.usecase.GetSeriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {


    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context) =
        AppDatabase.buildDatabase(context)


    @Provides
    @Singleton
    fun providesMoviesApi(): MoviesApi = BaseApi(MoviesApi::class.java, "https://api.tvmaze.com/")


    @Provides
    @Singleton
    fun provideMovieRepository(appDatabase: AppDatabase, moviesApi: MoviesApi) =
        MovieRepository(appDatabase, moviesApi)


    @Provides
    fun provideSeriesUseCase(movieRepository: MovieRepository) = GetSeriesUseCase(movieRepository)
}