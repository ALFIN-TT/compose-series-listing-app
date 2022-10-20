package com.example.movies.ui.screen.moviesscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.common.network.ApiException
import com.example.movies.common.network.Resource
import com.example.movies.data.repository.MovieRepository
import com.example.movies.data.usecase.GetSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val seriesUseCase: GetSeriesUseCase) :
    ViewModel() {

    private val _series = mutableStateOf(HomeState())
    val series: State<HomeState> = _series

    private fun getSeries() {
        seriesUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _series.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    _series.value = HomeState(data = it.data)
                }
                is Resource.Error -> {
                    _series.value = HomeState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        getSeries()
    }
}