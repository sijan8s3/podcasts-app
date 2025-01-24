package com.sijan.podcastsapp.podcasts_list.data.networking

import com.sijan.podcastsapp.core.data.networking.constructUrl
import com.sijan.podcastsapp.core.data.networking.safeApiCall
import com.sijan.podcastsapp.core.domain.utils.NetworkError
import com.sijan.podcastsapp.core.domain.utils.Result
import com.sijan.podcastsapp.core.domain.utils.map
import com.sijan.podcastsapp.podcasts_list.data.mappers.toPodcastList
import com.sijan.podcastsapp.podcasts_list.domain.Podcast
import com.sijan.podcastsapp.podcasts_list.data.networking.dto.PodcastListDto
import com.sijan.podcastsapp.podcasts_list.domain.PodcastDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemotePodcastDataSource(
    private val httpClient: HttpClient
): PodcastDataSource {
    override suspend fun getPodcastList(): Result<List<Podcast>, NetworkError> {
        return safeApiCall<PodcastListDto> {
            httpClient.get(
                urlString = constructUrl(url = "/best_podcasts")
            )
        }.map { response ->
            response.toPodcastList()
        }
    }

}