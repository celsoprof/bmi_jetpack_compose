package com.example.imcapp.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.imcapp.R
import com.example.imcapp.calcs.bmiCalc
import com.example.imcapp.model.BmiState
import java.util.Locale

@Composable
fun ResultScreen(modifier: Modifier = Modifier, navController: NavHostController?) {

    val context = LocalContext.current

    val sharedNome = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)

    val age = sharedNome.getString("age", "32")
    val weight = sharedNome.getString("weight", "99")
    val height = sharedNome.getString("height", "179")

    val bmi = bmiCalc(weight!!.toInt(), height!!.toDouble())



    val saudacao = "Your BMI Result"

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFADE072), Color(0xFF065A52))
    )

    var ageState by remember {
        mutableStateOf(age)
    }

    var weightState by remember {
        mutableStateOf(weight)
    }

    var heightState by remember {
        mutableStateOf(height)
    }

    var isFemale by remember {
        mutableStateOf(true)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Blue

    ) {
        Box(modifier = Modifier.background(gradient)) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = saudacao,
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    ),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Card(
                            modifier = Modifier.size(130.dp),
                            shape = CircleShape,
                            border = BorderStroke(
                                width = 8.dp,
                                color = bmi.color //colorResource(id = R.color.light_green)
                            ),
                            colors = CardDefaults
                                .cardColors(
                                    containerColor = Color.White
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = String
                                        .format(
                                            locale = Locale.getDefault(),
                                            format = "%.1f",
                                            bmi.bmi.second
                                        ),
                                    fontSize = 44.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = bmi.bmi.first,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .height(80.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f)
                                ) {
                                    Text(text = "Age")
                                    Text(
                                        text = ageState ?: "",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                VerticalDivider()
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f)
                                ) {
                                    Text(text = "Weight")
                                    Text(
                                        text = "${weightState ?: ""} Kg",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                VerticalDivider()
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f)
                                ) {
                                    Text(text = "High")
                                    Text(
                                        text = "${heightState ?: ""} cm",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        BmiLevels(
                            levelText = "Underweight",
                            levelNumber = "<18.5",
                            markColor = colorResource(id = R.color.light_blue),
                            cardColor = bmi.color,
                            isMarked = bmi.bmiState == BmiState.UNDERWEIGHT
                        )
                        BmiLevels(
                            levelText = "Normal",
                            levelNumber = "18.6 - 24.9",
                            markColor = colorResource(id = R.color.light_green),
                            cardColor = bmi.color,
                            isMarked = bmi.bmiState == BmiState.NORMAL
                        )
                        BmiLevels(
                            levelText = "Overweight",
                            levelNumber = "25.0 - 29.9",
                            markColor = colorResource(id = R.color.yellow),
                            cardColor = bmi.color,
                            isMarked = bmi.bmiState == BmiState.OVERWEIGHT
                        )
                        BmiLevels(
                            levelText = "Obesity class I",
                            levelNumber = "30.0 - 34.9",
                            markColor = colorResource(id = R.color.light_orange),
                            cardColor = bmi.color,
                            isMarked = bmi.bmiState == BmiState.OBESITY1
                        )
                        BmiLevels(
                            levelText = "Obesity class II",
                            levelNumber = "35.0 - 39.9",
                            markColor = colorResource(id = R.color.black_orange),
                            cardColor = bmi.color,
                            isMarked = bmi.bmiState == BmiState.OBESITY2
                        )
                        BmiLevels(
                            levelText = "Obesity class III",
                            levelNumber = ">39.9",
                            markColor = colorResource(id = R.color.red),
                            cardColor = bmi.color,
                            isMarked = bmi.bmiState == BmiState.OBESITY3
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 32.dp))

                        Button(
                            onClick = {
                                navController!!.navigate("imc")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults
                                .buttonColors(containerColor = colorResource(id = R.color.green))
                        ) {
                            Text(
                                text = "New Calc",
                                fontSize = 24.sp
                            )
                        }
                    }
                } // FIM CARD
            } // FIM COLUMN 1
        } // FIM BOX
    }

}

@Composable
fun BmiLevels(
    modifier: Modifier = Modifier,
    levelText: String = "",
    levelNumber: String = "",
    markColor: Color = Color.Red,
    cardColor: Color = Color.Transparent,
    isMarked: Boolean = false
) {
    Row(
        verticalAlignment = Alignment
            .CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
    ) {
        Card(
            modifier = Modifier
                .size(18.dp),
            colors = CardDefaults
                .cardColors(containerColor = markColor),
            shape = CircleShape
        ) {}
        Card(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
                .height(38.dp),
            colors = CardDefaults
                .cardColors(
                    containerColor = if(isMarked) cardColor else Color.Transparent
                ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = levelText,
                    color = if(isMarked) Color.White else Color.DarkGray,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = levelNumber,
                    color = if(isMarked) Color.White else Color.DarkGray,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ImcScreenPreview() {
    ResultScreen(navController = null)
}



