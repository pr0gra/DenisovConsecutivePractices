package com.oolexander.rickandmorty.presentation.ui_kit.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oolexander.rickandmorty.domain.model.CharacterStatus
import com.oolexander.rickandmorty.presentation.screen.common.getStatusColor

@Composable
fun Avatar(
    model: Any?,
    characterStatus: CharacterStatus,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter)
                .background(
                    color = Color(0xFF2D3047),
                    shape = RoundedCornerShape(20.dp)
                )
        )

        AsyncImage(
            model = model,
            contentDescription = "avatar",
            modifier = Modifier
                .size(220.dp)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 4.dp,
                    color = getStatusColor(characterStatus),
                    shape = RoundedCornerShape(20.dp)
                ),
            contentScale = ContentScale.Crop
        )

        content()
    }
}