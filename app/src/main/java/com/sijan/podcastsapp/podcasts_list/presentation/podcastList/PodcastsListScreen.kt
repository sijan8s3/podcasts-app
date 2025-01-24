package com.sijan.podcastsapp.podcasts_list.presentation.podcastList

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sijan.podcastsapp.core.presentation.utils.ObserveAsEvents
import com.sijan.podcastsapp.core.presentation.utils.toString
import com.sijan.podcastsapp.podcasts_list.domain.Podcast
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListAction
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListEvent
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListState
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListViewModel
import com.sijan.podcastsapp.podcasts_list.presentation.podcastList.components.PodcastItem

@Composable
fun PodcastsListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: PodcastsListViewModel,
    onPodcastClicked: (Podcast) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    //observe events for errors
    ObserveAsEvents(events = viewModel.events) { event ->
        when (event) {
            is PodcastsListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    PodcastsListScreen(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        state = state,
        onAction = { action ->
            when (action) {
                is PodcastsListAction.OnPodcastClicked -> {
                    onPodcastClicked(action.podcast)
                }

                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}


@Composable
fun PodcastsListScreen(
    modifier: Modifier = Modifier,
    state: PodcastsListState,
    onAction: (PodcastsListAction) -> Unit
) {
    val podcastListState = rememberLazyListState()
    val context = LocalContext.current


    Column(modifier = modifier) {
        // Heading
        Text(
            text = "Podcasts",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp),
        )

        // Loading indicator
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // Error message and retry button
        if (state.errorMessage != null && !state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.errorMessage.toString(context = context))
                Button(onClick = {
                    onAction(PodcastsListAction.OnRetryClicked)
                }) {
                    Text(text = "Retry")
                }
            }
        }

        LazyColumn(
            state = podcastListState,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            items(
                items = state.podcasts,
                key = { podcast -> podcast.id }
            ) { podcast ->
                PodcastItem(
                    modifier = Modifier
                        .widthIn(max = 700.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .clickable(
                            onClick = {
                                onAction(PodcastsListAction.OnPodcastClicked(podcast))
                            }
                        ),
                    podcast = podcast,
                )

            }


        }

    }


}