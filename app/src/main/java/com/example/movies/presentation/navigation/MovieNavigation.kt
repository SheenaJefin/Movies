package com.example.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.presentation.screens.MovieList

@Composable
fun MovieNavigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = MovieNavigationItem.MovieList.route
    ) {

        composable(MovieNavigationItem.MovieList.route) {
            MovieList(navHostController)
        }
    }

}