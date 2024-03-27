package com.example.movies.movies_list

import com.example.movies.domain.model.MovieList.Movie
import com.example.movies.domain.repository.GetMovieListRepository
import com.example.movies.domain.use_case.GetMovieListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieListUseCaseTest {
    private lateinit var getMovieListRepository: GetMovieListRepository
    private lateinit var getMovieListUseCase: GetMovieListUseCase
    private val movieList  = Mockito.mock<List<Movie>>()

    @Before
    fun setUp(){
        getMovieListRepository = Mockito.mock()
        getMovieListUseCase = GetMovieListUseCase(getMovieListRepository)

    }

    @Test
    fun returnMovieFromUseCaseInSuccess() = runTest{
        Mockito.`when`(getMovieListRepository.getMovies()).thenReturn(
            movieList
        )
        getMovieListUseCase.invoke().onEach {
            Assert.assertEquals(movieList,it.data)
        }
    }

    @Test
    fun returnErrorFromUseCaseInSuccess() = runTest{
        Mockito.`when`(getMovieListRepository.getMovies()).thenThrow(
            RuntimeException("An error occurred")
        )
        getMovieListUseCase.invoke().onEach {
            Assert.assertEquals("An error occurred",it.message)
        }
    }
}