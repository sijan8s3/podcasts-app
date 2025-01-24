package com.sijan.podcastsapp.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sijan.podcastsapp.podcasts_list.presentation.podcastList.PodcastsListScreenRoot
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListViewModel
import org.koin.androidx.compose.koinViewModel

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
                val backStackEntry =
                    remember { navController.getBackStackEntry(NavigationRoute.PodcastGraph) }
                val viewModel: PodcastsListViewModel =
                    koinViewModel(viewModelStoreOwner = backStackEntry)

                PodcastsListScreenRoot(
                    viewModel = viewModel,
                )
            }

        }

    }

}
