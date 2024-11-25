package com.example.imcapp.model

import androidx.compose.ui.graphics.Color

data class Bmi(
  var bmi: Pair<String, Double>,
  var bmiState: BmiState = BmiState.NORMAL,
  var color: Color = Color.White
)