package com.vinaybyte.sample.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vinaybyte.sample.data.model.MovieItem
import com.vinaybyte.sample.ui.components.AppBar
import com.vinaybyte.sample.ui.navigation.Navigation
import com.vinaybyte.sample.ui.navigation.navigationTitle

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberAnimatedNavController()
    val movies: List<MovieItem> by viewModel.movies.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading

    ConstraintLayout {
        val (body, progress) = createRefs()
        Scaffold(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            topBar = { AppBar(title = navigationTitle(navController)) },
            modifier = Modifier.constrainAs(body) {
                top.linkTo(parent.top)
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Navigation(navController, modifier, movies)
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(progress) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}
