package com.vinaybyte.sample.ui.navigation

import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.vinaybyte.sample.R
import com.vinaybyte.sample.data.model.MovieItem
import com.vinaybyte.sample.ui.navigation.NavScreen.HomeScreen
import com.vinaybyte.sample.ui.screens.HomeScreen

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
@Composable
@ExperimentalAnimationApi
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
    data: Any? = null
) {
    val activity = LocalContext.current
    AnimatedNavHost(
        navController = navController,
        modifier = modifier,
        startDestination = HomeScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = HomeScreen.route
        ) {
            if (data is List<*> && data.all { it is MovieItem }) {
                HomeScreen(navController = navController,
                    movies = data as List<MovieItem>,
                    onItemClick = {
                        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        HomeScreen.route -> stringResource(id = R.string.home)
        NavScreen.DetailsScreen.route -> stringResource(id = R.string.details)
        else -> {
            ""
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
