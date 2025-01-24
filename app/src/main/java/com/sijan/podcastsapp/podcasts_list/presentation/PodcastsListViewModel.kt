package com.sijan.podcastsapp.podcasts_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sijan.podcastsapp.core.domain.utils.onError
import com.sijan.podcastsapp.core.domain.utils.onSuccess
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
    private val podcastDataSource: PodcastDataSource
):ViewModel() {

    private val _state = MutableStateFlow(PodcastsListState())
    val state = _state
        .onStart {
            loadPodcasts()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    private val _events = Channel<PodcastsListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: PodcastsListAction) {
        when(action) {
            is PodcastsListAction.OnPodcastClicked -> {
                _state.value = _state.value.copy(
                    selectedPodcast = action.podcast
                )
            }
            is PodcastsListAction.OnFavouriteToggled -> {

            }
            PodcastsListAction.OnRetryClicked -> loadPodcasts()
            else -> Unit
        }
    }

    private fun loadPodcasts() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val result = podcastDataSource.getPodcastList()
            result.onSuccess { podcasts ->
                _state.update {
                    it.copy(
                        podcasts = podcasts,
                        isLoading = false
                    )
                }
                }.onError { error ->
                _state.update {
                    it.copy(
                        errorMessage = error,
                        isLoading = false
                    )
                }
                _events.send(PodcastsListEvent.Error(error))
            }
        }
    }


}