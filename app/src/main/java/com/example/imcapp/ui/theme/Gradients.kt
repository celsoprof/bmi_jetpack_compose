package com.example.imcapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun HorizontalGradient(function: @Composable () -> Unit) {

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF009688), Color(0xFF2196F3))
    )
    
    Box(modifier = Modifier.background(gradient))

}