package com.example.movies.domain.use_case

import com.example.movies.core.common.UiState
import com.example.movies.domain.model.MovieList.Movie
import com.example.movies.domain.repository.GetMovieListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val getMovieListRepository: GetMovieListRepository) {

    operator fun invoke(): Flow<UiState<List<Movie>>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = getMovieListRepository.getMovies()))
        } catch (e: Exception) {
            emit(UiState.Error(message = e.message.toString()))
        }
    }
}