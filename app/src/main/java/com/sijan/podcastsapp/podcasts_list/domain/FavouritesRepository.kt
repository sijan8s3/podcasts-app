package com.sijan.podcastsapp.podcasts_list.domain

import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun isFavourite(podcastId: String): Boolean
    suspend fun addAsFavourite(podcastId: String)
    suspend fun removeFromFavourites(podcastId: String)
    fun getFavouritePodcastsFlow(): Flow<Set<String>>
}