package com.example.movies

import com.example.movies.model.Movie
import com.example.movies.remote.ApiService
import com.example.movies.viewModel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // Mock ApiService
    @Mock
    private lateinit var mockApiService: ApiService

    // Subject under test
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel()
    }
    @Test
    fun `test successful movie list retrieval`() = runBlocking {
        // Mock successful response from ApiService
        val movieList = listOf(
            Movie("Movie 1","https://howtodoandroid.com/images/coco.jpg","Movie1 is a 2017 American 3D computer-animated musical","Latest"),
            Movie("Movie 2","\"https://howtodoandroid.com/images/coco.jpg","Movie2 is a 2019 American movie","Favorites")
        )
        `when`(mockApiService.getMovies()).thenReturn(movieList)

        // Set ApiService instance in the ViewModel
        ApiService.apiService = mockApiService

        // Call the method to get movie list
        mainViewModel.getMovieList()

        // Ensure movie list is updated
        assertEquals(movieList, mainViewModel.movieListResponse)
        assertEquals("", mainViewModel.errorMessage)
    }
}