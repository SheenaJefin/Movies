package com.example.movies.movies_list

import com.example.movies.MainCoroutineRule
import com.example.movies.data.network.ApiService
import com.example.movies.data.repository.GetMoviesListRepositoryImpl
import com.example.movies.domain.model.MovieList.Movie
import com.example.movies.domain.repository.GetMovieListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieListRepositoryTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private lateinit var getMovieRepository: GetMovieListRepository
    private lateinit var apiService: ApiService
    private val movieList: List<Movie>? = Mockito.mock()

    @Before
    fun setUp() {
        apiService = Mockito.mock()
        getMovieRepository = GetMoviesListRepositoryImpl(apiService)
    }

    @Test
    fun returnSuccessWhenGetDataFromBackend() = runTest {
        Mockito.`when`(getMovieRepository.getMovies()).thenReturn(
            movieList
        )
        val result = getMovieRepository.getMovies()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(movieList, result)
    }
}