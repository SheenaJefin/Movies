package com.example.movies.presentation.stateholders

import com.example.movies.domain.model.MovieList.Movie

data class MovieStateHolder(
    val isLoading: Boolean = false,
    val data: List<Movie>? = null,
    val error: String = ""
)
