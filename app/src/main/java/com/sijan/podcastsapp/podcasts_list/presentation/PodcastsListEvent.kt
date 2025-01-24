package com.sijan.podcastsapp.podcasts_list.presentation

import com.sijan.podcastsapp.core.domain.utils.NetworkError

sealed interface PodcastsListEvent {
    data class Error(val error: NetworkError): PodcastsListEvent
}