package com.sijan.podcastsapp.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * Composable function to set up the app's navigation graph.
 *
 * Defines the navigation structure and routes for the app's screens
 */

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.PodcastGraph,
    ) {

        navigation<NavigationRoute.PodcastGraph>(
            startDestination = NavigationRoute.PodcastList,
        ) {
            composable<NavigationRoute.PodcastList>{

            }

        }

    }

}
