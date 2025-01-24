package com.sijan.podcastsapp.podcasts_list.presentation.podcastDetails


import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.sijan.podcastsapp.R
import com.sijan.podcastsapp.core.presentation.utils.ObserveAsEvents
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListAction
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListEvent
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListState
import com.sijan.podcastsapp.podcasts_list.presentation.PodcastsListViewModel
import com.sijan.podcastsapp.podcasts_list.presentation.podcastDetails.components.HeaderBackIcon


@Composable
fun PodcastDetailsScreenRoot(
    viewModel: PodcastsListViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PodcastDetailsScreen(
        modifier = Modifier.systemBarsPadding(),
        state = state,
        onAction = viewModel::onAction,
        onNavigateBack = {
            onNavigateBack()
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PodcastDetailsScreen(
    modifier: Modifier = Modifier,
    state: PodcastsListState,
    onAction: (PodcastsListAction) -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        HeaderBackIcon(onClickBack = {
            onNavigateBack()
        })
        state.selectedPodcast?.let { podcast ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Text(
                        text = podcast.title,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                    )
                    podcast.publisher?.let {
                        Text(
                            text = podcast.publisher,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            fontStyle = FontStyle.Italic,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    // Image Section
                    AsyncImage(
                        model = podcast.image,
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(250.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Author Section
                    Button(
                        onClick = { /* Handle favourite click */ },
                        shape = MaterialTheme.shapes.medium,
                    ) {
                        Text(
                            "Favourite"
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Description Section
                    podcast.description?.let {
                        Text(
                            text = podcast.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        }
    }
}
