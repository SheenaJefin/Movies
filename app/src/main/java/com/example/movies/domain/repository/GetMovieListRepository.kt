package com.example.movies.domain.repository

import com.example.movies.domain.model.MovieList.Movie

interface GetMovieListRepository {
    suspend fun getMovies(): List<Movie>
}