package com.example.movies.movies_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.movies.domain.model.MovieList.Movie
import com.example.movies.presentation.components.MovieItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieItemDisplayTest {

    @get:Rule
    val composerule = createComposeRule()

    @Before
    fun setUp() {
        composerule.setContent {
            MovieItem()
        }
    }

    @Test
    fun beDispalyed() {
        composerule.onNodeWithTag("movieItem").assertIsDisplayed()
    }

    @Test
    fun containCorrectName() {
        composerule.onNodeWithText("Coco")
    }
}