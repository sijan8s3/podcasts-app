package com.sijan.podcastsapp.podcasts_list.domain

data class PodcastList(
    val podcasts: List<Podcast>,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean,
    val nextPageNumber: Int,
    val pageNumber: Int,
    val previousPageNumber: Int,
    val total: Int
)

data class Podcast(
    val id: String,
    val title: String,
    val publisher: String? = null,
    val image: String? = null,
    val thumbnail: String? = null,
    val description: String? = null,
    val isFavourite: Boolean = false
)