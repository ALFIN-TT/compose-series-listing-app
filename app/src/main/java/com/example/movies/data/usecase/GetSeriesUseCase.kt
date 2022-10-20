package com.example.movies.data.usecase

import com.example.movies.common.network.ApiException
import com.example.movies.common.network.Resource
import com.example.movies.data.model.Movie
import com.example.movies.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(private val blogsRepository: MovieRepository) {

    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val response = blogsRepository.getMovies()
            emit(Resource.Success(data = response))
        } catch (e: ApiException) {
            emit(Resource.Error(e.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }

    }
}