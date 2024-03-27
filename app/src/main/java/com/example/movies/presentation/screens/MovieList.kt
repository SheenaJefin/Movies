package com.example.movies.presentation.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movies.presentation.components.MovieItem
import com.example.movies.presentation.viewModel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieList(navHostController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {

    Box(modifier = Modifier.padding(20.dp)) {
        val result = viewModel.movieListStateHolder.value

        if (result.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.testTag("progress"))
            }
        }

        if (result.error.isNotBlank()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result.error)
            }
        }


        if (result.data != null) {
            var selectedIndex by remember { mutableIntStateOf(-1) }
            LazyColumn(modifier = Modifier.testTag("movie_list")) {
                itemsIndexed(items = result.data) { index, item ->
                    MovieItem(movie = item, index, selectedIndex) { i ->
                        selectedIndex = i
                    }
                }
            }
        }
    }


}