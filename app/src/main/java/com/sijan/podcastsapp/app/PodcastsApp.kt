package com.sijan.podcastsapp.app

import android.app.Application
import com.sijan.podcastsapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Application class for the Podcasts App
 *
 * This class is responsible for initializing global app configurations,
 * including starting the Koin Dependency Injection framework.
 */

class PodcastsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger() // Log Koin-related events
            androidContext(this@PodcastsApp) // Provide the application context to Koin
            modules(appModule) // Load the app's dependency module
        }
    }
}
