package com.example.movies.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.core.common.UiState
import com.example.movies.domain.use_case.GetMovieListUseCase
import com.example.movies.presentation.stateholders.MovieStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieListUseCase: GetMovieListUseCase) : ViewModel() {

    private val _movieListStateHolder = mutableStateOf(MovieStateHolder())
    val movieListStateHolder : State<MovieStateHolder> = _movieListStateHolder

    init {
        getMovieList()
    }
    private fun getMovieList() = viewModelScope.launch {

        movieListUseCase().onEach {
            when(it){
                is UiState.Loading -> {
                    _movieListStateHolder.value = MovieStateHolder(isLoading = true)
                }
                is UiState.Success -> {
                    _movieListStateHolder.value = MovieStateHolder(data = it.data)
                }
                is UiState.Error -> {
                    _movieListStateHolder.value = MovieStateHolder(error= it.message.toString())
                }

            }
        }.launchIn(viewModelScope)

    }
}