package com.example.imcapp.calcs

import com.example.imcapp.model.Bmi
import com.example.imcapp.model.BmiState
import kotlin.math.pow


fun bmiCalc(weight: Int, height: Double, gender: String = ""): Bmi {

  val bmi = weight / height.div(100).pow(2)

  when {
    bmi < 18.5 ->
      return Bmi(
        bmi = Pair("You are below your ideal weight.", bmi),
        bmiState = BmiState.UNDERWEIGHT
      )

    bmi >= 18.5 && bmi < 25.0 ->
      return Bmi(
        bmi = Pair("You have normal body weight.", bmi),
        bmiState = BmiState.NORMAL
      )

    bmi > 25.0 && bmi < 30.0 ->
      return Bmi(
        bmi = Pair("You are slightly overweight.", bmi),
        bmiState = BmiState.OVERWEIGHT
      )

    bmi > 30.0 && bmi < 35.0 ->
      return Bmi(
        bmi = Pair("You have Class I Obesity.", bmi),
        bmiState = BmiState.OBESITY1
      )

    bmi > 35.0 && bmi < 40.0 ->
      return Bmi(
        bmi = Pair("You have Class II Obesity.", bmi),
        bmiState = BmiState.OBESITY2
      )
    else ->
      return Bmi(
        bmi = Pair("You have Class III Obesity.", bmi),
        bmiState = BmiState.OBESITY3
      )
  }

}

