package com.example.pr4_calc

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pr4_calc.services.Calc
import com.example.pr4_calc.ui.calc1.Calculator1Screen
import com.example.pr4_calc.ui.calc2.Calculator2Screen
import com.example.pr4_calc.ui.calc3.Calculator3Screen
import com.example.pr4_calc.ui.theme.Pr4_calcTheme
import com.example.pr4_calc.ui.entry.EntryScreen

enum class Routes {
    MAIN_SCREEN,
    CALCULATOR_1,
    CALCULATOR_2,
    CALCULATOR_3
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr4_calcTheme {
                val navController = rememberNavController()
                val calculator = Calc()
                Scaffold(modifier = Modifier.padding(60.dp, 200.dp)) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.MAIN_SCREEN.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Routes.MAIN_SCREEN.name) {
                            EntryScreen(
                                onCalculator1Navigate = { navController.navigate(route = Routes.CALCULATOR_1.name) },
                                onCalculator2Navigate = { navController.navigate(route = Routes.CALCULATOR_2.name) },
                                onCalculator3Navigate = { navController.navigate(route = Routes.CALCULATOR_3.name) },
                            )
                        }
                        composable(route = Routes.CALCULATOR_1.name) {
                            Calculator1Screen(
                                goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name )},
                                goNext = { navController.navigate(route = Routes.CALCULATOR_2.name )},
                                calculator = calculator
                            )
                        }
                        composable(route = Routes.CALCULATOR_2.name) {
                            Calculator2Screen(
                                goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name )},
                                goNext = { navController.navigate(route = Routes.CALCULATOR_3.name )},
                                calculator = calculator
                            )
                        }
                        composable(route = Routes.CALCULATOR_3.name) {
                            Calculator3Screen(goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name )},
                                calculator = calculator
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pr4_calcTheme {
        Greeting("Android")
    }
}

fun get_value() :String{
    val calc = Calc()
    val s = calc.get_s(43.toDouble())
    val l_s = 1.54625
    val (R, X) = calc.get_wire_resistance(s, l_s)
    return "A-$s: R = $R; X = $X"
}