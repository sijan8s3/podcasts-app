package com.sijan.podcastsapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import com.sijan.podcastsapp.ui.theme.PodcastsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PodcastsAppTheme {
                val navController = rememberNavController()

                AppNavigation(
                    navController = navController,
                )
            }
        }
    }
}
