package com.example.imcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imcapp.screens.ImcScreen
import com.example.imcapp.screens.ResultScreen
import com.example.imcapp.screens.WelcomeScreen
import com.example.imcapp.ui.theme.ImcAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ImcAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") {
                            WelcomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(route = "imc") {
                            ImcScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(route = "result") {
                            ResultScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                    }

                }
            }
        }
    }
}