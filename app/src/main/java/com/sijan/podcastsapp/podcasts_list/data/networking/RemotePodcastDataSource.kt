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
import com.sijan.podcastsapp.podcasts_list.domain.PodcastList
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemotePodcastDataSource(
    private val httpClient: HttpClient
): PodcastDataSource {
    override suspend fun getPodcastList(page: Int, pageSize: Int): Result<PodcastList, NetworkError> {
        return safeApiCall<PodcastListDto> {
            httpClient.get(urlString = constructUrl(url = "/best_podcasts")){
                parameter("page", page)
                parameter("page_size", pageSize)
            }
        }.map { response ->
            response.toPodcastList()
        }
    }

}