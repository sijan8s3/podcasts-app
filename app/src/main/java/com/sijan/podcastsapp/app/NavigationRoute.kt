package com.sijan.podcastsapp.app

import kotlinx.serialization.Serializable

sealed interface NavigationRoute{
    @Serializable
    data object PodcastGraph: NavigationRoute

    @Serializable
    data object PodcastList: NavigationRoute

    @Serializable
    data class PodcastDetail(val podcastId: Int): NavigationRoute

}