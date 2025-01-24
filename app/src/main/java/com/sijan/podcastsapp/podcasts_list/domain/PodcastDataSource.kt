package com.sijan.podcastsapp.podcasts_list.domain

import com.sijan.podcastsapp.core.domain.utils.NetworkError
import com.sijan.podcastsapp.core.domain.utils.Result

interface PodcastDataSource {
    suspend fun getPodcastList(): Result<List<Podcast>, NetworkError>
}