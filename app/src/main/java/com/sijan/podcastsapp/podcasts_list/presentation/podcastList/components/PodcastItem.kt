package com.sijan.podcastsapp.podcasts_list.presentation.podcastList.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sijan.podcastsapp.R
import com.sijan.podcastsapp.podcasts_list.domain.Podcast
import com.sijan.podcastsapp.ui.theme.PodcastsAppTheme

@Composable
fun PodcastItem(
    modifier: Modifier = Modifier,
    podcast: Podcast,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp)),
                model = podcast.thumbnail,
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = podcast.title,
                contentScale = ContentScale.Crop,
                onError = {
                    Log.e("AsyncImage", "Error loading image: ${it.result.throwable.message}")
                }
                )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = podcast.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    )

                podcast.publisher?.let { publisher ->
                    Text(
                        text = publisher,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PodcastItemPreview() {
    PodcastsAppTheme {
        PodcastItem(
            podcast = Podcast(
                id = "1",
                title = "Podcast Title",
                publisher = "Podcast Publisher",
                thumbnail = "https://picsum.photos/200"
            )
        )
    }
}