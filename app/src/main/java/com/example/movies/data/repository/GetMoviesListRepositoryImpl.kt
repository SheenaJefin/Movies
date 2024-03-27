package com.example.movies.data.repository

import com.example.movies.data.network.ApiService
import com.example.movies.domain.model.MovieList.Movie
import com.example.movies.domain.repository.GetMovieListRepository
import javax.inject.Inject

class GetMoviesListRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetMovieListRepository {
    override suspend fun getMovies(): List<Movie> {
        return apiService.getMovies()
    }
}