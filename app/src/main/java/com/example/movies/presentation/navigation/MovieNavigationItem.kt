package com.example.movies.presentation.navigation

sealed class MovieNavigationItem(val route: String) {

    data object MovieList : MovieNavigationItem("movie_list")
}