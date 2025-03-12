package com.nolwendroid.cinetune.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFD7CEC1), // Светлый бежевый
    secondary = Color(0xFFC5B299), // Немного темнее основного
    background = Color(0xFFF5F2EB), // Почти белый
    surface = Color(0xFFEDE8E0), // Светло-бежевый
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color(0xFFD32F2F) // Красный для ошибок
)

private val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)

@Composable
fun CineTuneTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
