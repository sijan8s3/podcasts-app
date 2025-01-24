package com.sijan.podcastsapp.podcasts_list.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PodcastListDto(
    val has_next: Boolean,
    val has_previous: Boolean,
    val id: Int,
    val listennotes_url: String,
    val name: String,
    val next_page_number: Int,
    val page_number: Int,
    val parent_id: Int,
    val podcasts: List<Podcast>,
    val previous_page_number: Int,
    val total: Int
)

@Serializable
data class Podcast(
    val audio_length_sec: Int,
    val country: String,
    val description: String,
    val earliest_pub_date_ms: Long,
    val email: String,
    val explicit_content: Boolean,
    val extra: Extra,
    val genre_ids: List<Int>,
    val has_guest_interviews: Boolean,
    val has_sponsors: Boolean,
    val id: String,
    val image: String,
    val is_claimed: Boolean,
    val itunes_id: Int,
    val language: String,
    val latest_episode_id: String,
    val latest_pub_date_ms: Long,
    val listen_score: Int,
    val listen_score_global_rank: String,
    val listennotes_url: String,
    val looking_for: LookingFor,
    val publisher: String,
    val rss: String,
    val thumbnail: String,
    val title: String,
    val total_episodes: Int,
    val type: String,
    val update_frequency_hours: Int,
    val website: String
)

@Serializable
data class Extra(
    val amazon_music_url: String,
    val facebook_handle: String,
    val instagram_handle: String,
    val linkedin_url: String,
    val patreon_handle: String,
    val spotify_url: String,
    val twitter_handle: String,
    val url1: String,
    val url2: String,
    val url3: String,
    val wechat_handle: String,
    val youtube_url: String
)

@Serializable
data class LookingFor(
    val cohosts: Boolean,
    val cross_promotion: Boolean,
    val guests: Boolean,
    val sponsors: Boolean
)