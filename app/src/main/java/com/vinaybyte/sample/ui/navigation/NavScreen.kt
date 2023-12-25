package com.vinaybyte.sample.ui.navigation

import com.vinaybyte.sample.Constants.HOME_SCREEN
import com.vinaybyte.sample.Constants.MOVIES_DETAILS_SCREEN

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(HOME_SCREEN)
    object DetailsScreen : NavScreen(MOVIES_DETAILS_SCREEN) {
        const val routeWithArgument: String = "MoviesDetails/{id}"
        const val argument0: String = "id"
    }
}