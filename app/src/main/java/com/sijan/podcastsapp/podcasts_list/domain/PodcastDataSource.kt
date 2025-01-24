package com.sijan.podcastsapp.podcasts_list.domain

import com.sijan.podcastsapp.core.domain.utils.NetworkError
import com.sijan.podcastsapp.core.domain.utils.Result

interface PodcastDataSource {
    suspend fun getPodcastList(page: Int, pageSize: Int = 10): Result<PodcastList, NetworkError>
}