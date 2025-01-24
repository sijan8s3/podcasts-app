package com.sijan.podcastsapp.di

import com.sijan.podcastsapp.core.data.networking.HttpClientFactory
import com.sijan.podcastsapp.podcasts_list.data.networking.RemotePodcastDataSource
import com.sijan.podcastsapp.podcasts_list.domain.PodcastDataSource
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.sijan.podcastsapp.core.data.local.dataStore
import com.sijan.podcastsapp.podcasts_list.domain.FavouritesRepository
import com.sijan.podcastsapp.podcasts_list.data.local.LocalFavouritesRepository



// Koin module for dependency injection
// Define all app-specific dependencies here, such as:
// - ViewModels
// - Data Sources
// - Network Services

val appModule = module {
    single { androidContext().dataStore }

    single { HttpClientFactory.create(CIO.create()) }

    singleOf(::RemotePodcastDataSource).bind<PodcastDataSource>()

    viewModelOf(::PodcastsListViewModel)

    singleOf(::LocalFavouritesRepository).bind<FavouritesRepository>()

}