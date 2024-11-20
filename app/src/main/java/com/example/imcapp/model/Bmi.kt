package com.example.imcapp.model

data class Bmi(
  var bmi: Pair<String, Double>,
  var bmiState: BmiState = BmiState.NORMAL
)