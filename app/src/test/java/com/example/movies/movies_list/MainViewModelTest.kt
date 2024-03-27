package com.example.movies.movies_list

import com.example.movies.MainCoroutineRule
import com.example.movies.core.common.UiState
import com.example.movies.domain.model.MovieList.Movie
import com.example.movies.domain.use_case.GetMovieListUseCase
import com.example.movies.presentation.viewModel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getMovieListUseCase: GetMovieListUseCase = Mockito.mock()
    private lateinit var mainViewModel: MainViewModel
    private val movieList: List<Movie>? = Mockito.mock()

    @Before
    fun setup() {
        mainViewModel = MainViewModel(getMovieListUseCase)
    }

    @Test
    fun validateUserWillSeeProgressBarInitially() = runTest {
        Mockito.`when`(getMovieListUseCase.invoke()).thenReturn(
            flow {
                emit(UiState.Loading())
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(true, mainViewModel.movieListStateHolder.value.isLoading)
    }

    @Test
    fun validateSuccessStateIsStoredInMovieList() = runTest {
        Mockito.`when`(getMovieListUseCase.invoke()).thenReturn(
            flow {
                emit(UiState.Success(movieList))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(movieList, mainViewModel.movieListStateHolder.value.data)
    }



    @Test
    fun throwErrorInErrorCase() = runTest {
        Mockito.`when`(getMovieListUseCase.invoke()).thenReturn(
            flow {
                emit(UiState.Error("An error occurred"))
            }
        )
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals("An error occurred", mainViewModel.movieListStateHolder.value.error)
    }
}