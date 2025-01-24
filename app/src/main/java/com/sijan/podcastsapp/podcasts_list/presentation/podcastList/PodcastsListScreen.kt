package com.sijan.podcastsapp.podcasts_list.presentation.podcastList

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sijan.podcastsapp.core.presentation.utils.ObserveAsEvents
import com.sijan.podcastsapp.core.presentation.utils.toString
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListAction
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListEvent
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListState
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListViewModel
import com.sijan.podcastsapp.podcasts_list.presentation.podcastList.components.PodcastItem

@Composable
fun PodcastsListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: PodcastsListViewModel
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

            else -> Unit
        }
    }
    PodcastsListScreen(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        state = state,
        onAction = viewModel::onAction
    )

}


@Composable
fun PodcastsListScreen(
    modifier: Modifier = Modifier,
    state: PodcastsListState,
    onAction: (PodcastsListAction) -> Unit
) {
    Column(
        modifier = modifier
    ) {

        Text(
            text = "Podcasts",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp),
        )

        LazyColumn(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            items(
                items = state.podcasts,
                key = { podcast -> podcast.id }
            ) { podcast ->
                PodcastItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    podcast = podcast,
                )

            }


        }

    }


}