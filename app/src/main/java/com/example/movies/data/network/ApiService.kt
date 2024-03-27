package com.example.movies.data.network

import com.example.movies.domain.model.MovieList.Movie
import retrofit2.http.GET

interface ApiService {
    @GET("movielist.json")
    suspend fun getMovies(): List<Movie>
}