package com.sijan.podcastsapp.podcasts_list.presentation

import com.sijan.podcastsapp.core.domain.utils.NetworkError
import com.sijan.podcastsapp.podcasts_list.domain.Podcast

data class PodcastsListState(
    val isLoading: Boolean = false,
    val podcasts: List<Podcast> = emptyList(),
    val selectedPodcast: Podcast? = null,
    val errorMessage: NetworkError? = null,
    val favouritePodcasts: Set<String> = emptySet()

)