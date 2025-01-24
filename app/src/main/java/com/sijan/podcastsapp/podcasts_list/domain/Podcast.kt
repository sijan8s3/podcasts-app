package com.sijan.podcastsapp.podcasts_list.domain

data class Podcast(
    val id: String,
    val title: String,
    val publisher: String? = null,
    val image: String? = null,
    val thumbnail: String? = null,
    val description: String? = null,
)