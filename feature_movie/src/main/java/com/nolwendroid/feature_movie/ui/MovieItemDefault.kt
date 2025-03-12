package com.nolwendroid.feature_movie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.uicommon.draganddrop.DragTarget
import com.nolwendroid.core.R

@Composable
fun MovieItem(movie: MovieKnpUi) {
    DragTarget(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Transparent),
        dataToDrop = movie,
        draggable = {
            MoviePoster(movie)
        }
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(6.dp)
                .background(Color.Transparent),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp)
                    .background(Color.Transparent)
            ) {
                MoviePoster(movie)
                Spacer(modifier = Modifier.height(8.dp))
                //MovieTitle(movie)
            }
        }
    }
}

@Composable
fun MoviePoster(movie: MovieKnpUi) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(2.dp)
            .border(1.dp, Color(red = 200, green = 160, blue = 0), RoundedCornerShape(16.dp))
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .shadow(12.dp, RoundedCornerShape(16.dp))
                .background(Color.Transparent),
            elevation = CardDefaults.cardElevation(18.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            SubcomposeAsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(240.dp, 360.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                modifier = Modifier
                    .size(240.dp, 360.dp)
                    .background(Color.Transparent, RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun MovieTitle(movie: MovieKnpUi) {
    Text(
        text = movie.title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(Color.Transparent)
            .padding(top = 4.dp)
            .width(160.dp)
    )
    Text(
        text = "⭐ ${movie.rating}",
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier
            .background(Color.Transparent)
    )
}

