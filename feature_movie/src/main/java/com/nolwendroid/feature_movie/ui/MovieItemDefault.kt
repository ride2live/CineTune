package com.nolwendroid.feature_movie.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.uicommon.draganddrop.DragTarget

@Composable
fun MovieItem(movie: MovieKnpUi, onDragEnd: (MovieKnpUi) -> Unit) {
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    var isDragging by remember { mutableStateOf(false) }
    DragTarget(modifier = Modifier.wrapContentSize(), dataToDrop = movie, draggable = {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }) {
        Card(modifier = Modifier
            .zIndex(if (!isDragging) 2f else 0f)
            .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 0.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )


                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = movie.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Рейтинг: ${movie.rating}", fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}