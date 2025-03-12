package com.nolwendroid.feature_movie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.uicommon.draganddrop.DragTarget

@Composable
fun MovieItem(movie: MovieKnpUi) {
    DragTarget(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.Transparent),
        dataToDrop = movie,
        draggable = {
            Box(
                modifier = Modifier.graphicsLayer {
                    alpha = 0.9f // Делаем копию прозрачнее
                    translationX = 0f
                    translationY = 0f
                }
            ) {
                MoviePoster(movie)
            }
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
                Spacer(modifier = Modifier.height(12.dp))
                MovieTitle(movie)
            }
        }
    }
}

@Composable
fun MoviePoster(movie: MovieKnpUi) {
//    Box(
//        modifier = Modifier
//            .wrapContentSize()
//            .padding(2.dp)
//            .border(1.dp, Color(red = 200, green = 200, blue = 200), RoundedCornerShape(16.dp))
//    ) {
        Column {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .shadow(12.dp, RoundedCornerShape(16.dp))
                    .background(Color.Transparent)
                    .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
                elevation = CardDefaults.cardElevation(18.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                SubcomposeAsyncImage(
                    model = movie.posterUrl,
                    contentDescription = movie.title,
                    loading = {
                        Box(
                            modifier = Modifier
                                .size(120.dp, 180.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    modifier = Modifier
                        .size(120.dp, 180.dp)
                        .background(Color.Transparent, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }
//            Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
//            MovieTitle(movie)
        }
   // }
}

@Composable
fun MovieTitle(movie: MovieKnpUi) {
    Text(
        text = movie.title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 12.sp,
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

