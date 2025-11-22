package com.oolexander.rickandmorty.presentation.ui_kit.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(top = 20.dp),
        colors = ButtonColors(
            containerColor = Color(0xFF2D3047),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFF2D3047),
            disabledContentColor = Color.White,
        ),
    ) {
        Text(text)
    }
}