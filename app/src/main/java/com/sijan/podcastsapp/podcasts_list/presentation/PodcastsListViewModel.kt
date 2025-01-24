package com.sijan.podcastsapp.podcasts_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sijan.podcastsapp.core.domain.utils.onError
import com.sijan.podcastsapp.core.domain.utils.onSuccess
import com.sijan.podcastsapp.podcasts_list.domain.FavouritesRepository
import com.sijan.podcastsapp.podcasts_list.domain.Podcast
import com.sijan.podcastsapp.podcasts_list.domain.PodcastDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PodcastsListViewModel(
    private val podcastDataSource: PodcastDataSource,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {

    // StateFlow to hold the UI state
    private val _state = MutableStateFlow(PodcastsListState())
    val state = _state
        .onStart {
            loadPodcasts()
            observeFavouritePodcasts()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    // Pagination variables
    private var currentPage = 1
    private var isLoadingNextPage = false // Prevent duplicate requests
    private var hasMorePages = true

    // Channel for one-time events like error messages
    private val _events = Channel<PodcastsListEvent>()
    val events = _events.receiveAsFlow()

    // Handle UI actions
    fun onAction(action: PodcastsListAction) {
        when (action) {
            is PodcastsListAction.OnPodcastClicked -> selectPodcast(action.podcast)
            is PodcastsListAction.OnFavouriteToggled -> toggleFavourite(action.podcastId)
            PodcastsListAction.OnRetryClicked -> retryLoading()
            PodcastsListAction.OnNextPageRequested -> loadNextPage()
            else -> Unit
        }
    }

    // Select a podcast to view details
    private fun selectPodcast(podcast: Podcast) {
        _state.update { it.copy(selectedPodcast = podcast) }
    }

    // Select a podcast to view details
    private fun toggleFavourite(podcastId: String) {
        viewModelScope.launch {
            val isLiked = _state.value.favouritePodcasts.contains(podcastId)
            if (isLiked) favouritesRepository.removeFromFavourites(podcastId)
            else favouritesRepository.addAsFavourite(podcastId)

            _state.update { currentState ->
                val updatedFavIds = if (isLiked) {
                    currentState.favouritePodcasts - podcastId
                } else {
                    currentState.favouritePodcasts + podcastId
                }
                currentState.copy(favouritePodcasts = updatedFavIds)
            }
        }
    }

    // Retry loading podcasts in case of an error
    private fun retryLoading() {
        loadPodcasts(currentPage)
    }

    // Load podcasts from the data source
    private fun loadPodcasts(page: Int = 1) {
        if (isLoadingNextPage || !hasMorePages) return

        viewModelScope.launch {
            isLoadingNextPage = true
            _state.update { it.copy(isLoading = true) }

            podcastDataSource.getPodcastList(page)
                .onSuccess { podcastList ->
                    _state.update {
                        it.copy(
                            podcasts = if (page == 1) podcastList.podcasts else it.podcasts + podcastList.podcasts,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                    currentPage = podcastList.pageNumber
                    hasMorePages = podcastList.hasNextPage
                }
                .onError { error ->
                    _state.update { it.copy(errorMessage = error, isLoading = false) }
                    _events.send(PodcastsListEvent.Error(error))
                }

            isLoadingNextPage = false
        }
    }

    // Load the next page of podcasts
    private fun loadNextPage() {
        if (hasMorePages) {
            loadPodcasts(currentPage + 1)
        }
    }

    // Observe changes in favourite podcasts
    private fun observeFavouritePodcasts() {
        viewModelScope.launch {
            favouritesRepository.getFavouritePodcastsFlow().collect { podcasts ->
                _state.update { it.copy(favouritePodcasts = podcasts) }
            }
        }
    }


}