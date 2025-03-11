package com.nolwendroid.core.uicommon.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Close, contentDescription = "Закрыть")
    }
}

@Preview(showBackground = true)
@Composable
fun CloseButtonPreview() {
    CloseButton(onClick = {}) // Превью кнопки без действия
}
