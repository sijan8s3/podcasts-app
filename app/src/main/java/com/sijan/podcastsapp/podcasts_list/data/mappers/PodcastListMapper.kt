package com.sijan.podcastsapp.podcasts_list.data.mappers

import com.sijan.podcastsapp.podcasts_list.data.networking.dto.PodcastListDto
import com.sijan.podcastsapp.podcasts_list.domain.Podcast
import com.sijan.podcastsapp.podcasts_list.domain.PodcastList

fun PodcastListDto.toPodcastList(): PodcastList {
    return PodcastList(
        podcasts = podcasts.map { it.toDataPodcast() },
        hasNextPage = has_next,
        hasPreviousPage = has_previous,
        nextPageNumber = next_page_number,
        pageNumber = page_number,
        previousPageNumber = previous_page_number,
        total = total
    )
}


fun com.sijan.podcastsapp.podcasts_list.data.networking.dto.Podcast.toDataPodcast(): Podcast {
    return Podcast(
        id = id,
        title = title,
        publisher = publisher,
        image = image,
        thumbnail = thumbnail,
        description = description
    )
}