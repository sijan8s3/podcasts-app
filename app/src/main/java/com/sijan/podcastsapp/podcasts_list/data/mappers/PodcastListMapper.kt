package com.sijan.podcastsapp.podcasts_list.data.mappers

import com.sijan.podcastsapp.podcasts_list.data.networking.dto.PodcastListDto
import com.sijan.podcastsapp.podcasts_list.domain.Podcast

fun PodcastListDto.toPodcastList(): List<Podcast> {
    return podcasts.map { podcast ->
        podcast.toDataPodcast()
    }
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