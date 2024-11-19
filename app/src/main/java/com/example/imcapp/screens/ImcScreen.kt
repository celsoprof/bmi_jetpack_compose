package com.example.imcapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.imcapp.R


@Composable
fun ImcScreen(modifier: Modifier = Modifier, navController: NavHostController?) {

    val context = LocalContext.current

    val sharedUser = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)
    val nome = sharedUser.getString("nome", "")
    val saudacao = "Hi $nome!"

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFADE072), Color(0xFF065A52))
    )

    var ageState by remember {
        mutableStateOf("")
    }

    var weightState by remember {
        mutableStateOf("")
    }

    var heightState by remember {
        mutableStateOf("")
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
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = saudacao,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(48.dp))
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
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))

                            ManWoman(
                                isFemale = isFemale
                            ) {
                                Toast.makeText(context, "Alterado", Toast.LENGTH_SHORT).show()
                                isFemale = !isFemale
                            }

                            MyOutlinedTextField(
                                value = ageState,
                                label = "Age",
                                suffix = "years old",
                                leadingIcon = Icons.Default.Numbers,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                                onValueChange = {
                                    ageState = it
                                }
                            )

                            MyOutlinedTextField(
                                value = weightState,
                                label = "Weight",
                                suffix = "kgs",
                                leadingIcon = Icons.Default.Balance,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                                onValueChange = {
                                    weightState = it
                                }
                            )

                            MyOutlinedTextField(
                                value = heightState,
                                label = "Height",
                                suffix = "cms",
                                leadingIcon = Icons.Default.Height,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                onValueChange = {
                                    heightState = it
                                }
                            )
                        }

                        Button(
                            onClick = {
                                val editor = sharedUser.edit()
                                editor.putString("age", ageState)
                                editor.putString("weight", weightState)
                                editor.putString("height", heightState)
                                editor.apply()
                                navController?.navigate("result")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults
                                .buttonColors(
                                    containerColor = colorResource(id = R.color.green)
                                ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "Calculate",
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun MyOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    suffix: String = "",
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        label = {
            Text(text = label)
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "",
                    tint = Color(0xFF009688)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        suffix = {
            Text(text = suffix)
        }
    )

}

@Composable
fun ManWoman(
    modifier: Modifier = Modifier,
    isFemale: Boolean = true,
    onClick: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Card(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                border = BorderStroke(width = 2.dp, color = Color.Magenta)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = "man",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onClick,
                modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFemale) Color.Blue else colorResource(
                        id = R.color.green
                    )
                )
            ) {
                Text(text = "Male")
            }
        }
        Column(
            modifier = Modifier.padding(start = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                border = BorderStroke(width = 2.dp, color = Color.Magenta)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.woman),
                        contentDescription = "man",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            //
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onClick,
                modifier = Modifier
                    .width(150.dp),
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = if (isFemale) colorResource(id = R.color.green) else Color.Blue
                    )
            ) {
                Text(text = "Female")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ImcScreenPreview() {
    ImcScreen(navController = null)

}

@Preview
@Composable
private fun ManWomanPreview() {
    ManWoman(onClick = {})
}