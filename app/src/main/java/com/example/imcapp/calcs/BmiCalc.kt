package com.example.imcapp.calcs

import java.util.Locale
import kotlin.math.pow

fun bmiCalc(weight: Int, height: Double): String {
    val bmi = weight / height.div(100).pow(2)
    println(bmi)
    return String.format(
        locale = Locale.getDefault(),
        format = "%.1f", bmi)
}

fun bmiStatus(bmi: Double): String {

    if (bmi < 18.5){
        return "Your body is underweight."
    } else {
        return ""
    }

}