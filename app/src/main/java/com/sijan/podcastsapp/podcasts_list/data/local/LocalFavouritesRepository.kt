package com.sijan.podcastsapp.podcasts_list.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.sijan.podcastsapp.podcasts_list.domain.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
/**
 * Implementation of the FavouritesRepository interface using DataStore for local storage.
 * This repository provides functionality to manage a list of favourite podcasts, including:
 * - Checking if a podcast is marked as favourite.
 * - Adding podcasts to favourites.
 * - Removing podcasts from favourites.
 * - Observing changes to the favourite podcasts as a Flow.
 */



class LocalFavouritesRepository(
    private val dataStore: DataStore<Preferences>
): FavouritesRepository {

    // Key for storing favourite podcast IDs as strings
    private val favouritePodcastsKey = stringSetPreferencesKey("favourite_podcasts")

    // Check if an podcast is liked
    override suspend fun isFavourite(podcastId: String): Boolean {
        val likedPodcasts = dataStore.data.first()[favouritePodcastsKey] ?: emptySet()
        return likedPodcasts.contains(podcastId) 
    }

    // Like an podcast (add its ID to the set)
    override suspend fun addAsFavourite(podcastId: String) {
        dataStore.edit { preferences ->
            val currentLikes = preferences[favouritePodcastsKey] ?: emptySet()
            preferences[favouritePodcastsKey] = currentLikes + podcastId
        }
    }

    // Unlike an podcast (remove its ID from the set)
    override suspend fun removeFromFavourites(podcastId: String) {
        dataStore.edit { preferences ->
            val currentLikes = preferences[favouritePodcastsKey] ?: emptySet()
            preferences[favouritePodcastsKey] = currentLikes - podcastId
        }
    }

    // Get the set of liked podcasts as a Flow of String values
    override fun getFavouritePodcastsFlow(): Flow<Set<String>> {
        return dataStore.data.map { preferences ->
            preferences[favouritePodcastsKey]?.toSet() ?: emptySet()
        }
    }
    
}