package com.example.movies.domain.di

import com.example.movies.data.network.ApiService
import com.example.movies.data.repository.GetMoviesListRepositoryImpl
import com.example.movies.domain.repository.GetMovieListRepository
import com.example.movies.domain.use_case.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun providesGetMoviesRepository(apiService: ApiService): GetMovieListRepository {
        return GetMoviesListRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetMovieUseCase(getMovieRepository: GetMovieListRepository): GetMovieListUseCase {
        return GetMovieListUseCase(getMovieRepository)
    }
}