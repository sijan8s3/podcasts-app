package com.sijan.podcastsapp.podcasts_list.presentation

import com.sijan.podcastsapp.podcasts_list.domain.Podcast

sealed interface PodcastsListAction {
    data class OnPodcastClicked(val podcast: Podcast) : PodcastsListAction
    data object OnBackClicked : PodcastsListAction
    data object OnRetryClicked : PodcastsListAction
    data class OnFavouriteToggled(val podcastId: String) : PodcastsListAction
}