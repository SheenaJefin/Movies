package com.example.movies.movies_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.movies.presentation.MainActivity
import org.junit.Rule
import org.junit.Test

class MovieListDisplayTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateProgressBarVisible() {
        composeRule.apply {
            onNodeWithTag("progress").assertIsDisplayed()
        }
    }

    @Test
    fun validateIsMovieListVisible() {
        composeRule.apply {
            Thread.sleep(2000)
            onNodeWithTag("movie_list").assertIsDisplayed()
        }
    }
}