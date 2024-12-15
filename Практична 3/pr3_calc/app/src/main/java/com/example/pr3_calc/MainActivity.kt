package com.example.pr3_calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pr3_calc.services.Calc
import com.example.pr3_calc.ui.calcscreen.CalculatorScreen
import com.example.pr3_calc.ui.theme.Pr3_calcTheme
import org.apache.commons.math3.special.Erf

enum class Routes {
    MAIN_SCREEN,
    CALCULATOR
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr3_calcTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.CALCULATOR.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Routes.CALCULATOR.name) {
                            CalculatorScreen(
                                goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name) }
                            )
                        }
                    }
                }
            }
        }
    }
}


fun calcErf() :Double {
    val x = 1.0
    val result = Erf.erf(x)
    return result
}