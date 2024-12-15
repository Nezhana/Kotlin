package com.example.pr3_calc.ui.calcscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

// My Calc class:
import com.example.pr3_calc.services.Calc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CalculatorScreen(
    goBack: () -> Unit
) {
    var P by remember { mutableStateOf("") }
    var B by remember { mutableStateOf("") }
    var σ1 by remember { mutableStateOf("") }
    var σ2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculate(): Unit {

        val p1: Double = P.toDouble() + σ2.toDouble()
        val p2: Double = P.toDouble() - σ2.toDouble()

        val double_σ1 = σ1.toDouble()
        val double_σ2 = σ2.toDouble()
        val double_P = P.toDouble()
        val double_B = B.toDouble()

        val calculator = Calc(double_σ1, double_σ2, p1, p2)

        // Part 1:
        val F_p1_1: Double = calculator.F(p1, double_σ1, double_P)
        val F_p1_2: Double = calculator.F(p2, double_σ1, double_P)
        val δ_w_1: Double = calculator.δ_w(F_p1_1, F_p1_2)
        val opp_δ_w_1: Double = 1.0f - δ_w_1
        val W1_1: Double = calculator.W(δ_w_1, double_P)
        val W1_2: Double = calculator.W(opp_δ_w_1, double_P)
        val Profit1: Double = calculator.P(W1_1, double_B)
        val Penalty1: Double = calculator.P(W1_2, double_B)
        val summary1: Double = calculator.summary(Profit1, Penalty1)

        // Part 2:
        val F_p2_1: Double = calculator.F(p1, double_σ2, double_P)
        val F_p2_2: Double = calculator.F(p2, double_σ2, double_P)
        val δ_w_2: Double = calculator.δ_w(F_p2_1, F_p2_2)
        val opp_δ_w_2: Double = 1.0f - δ_w_2
        val W2_1: Double = calculator.W(δ_w_2, double_P)
        val W2_2: Double = calculator.W(opp_δ_w_2, double_P)
        val Profit2: Double = calculator.P(W2_1, double_B)
        val Penalty2: Double = calculator.P(W2_2, double_B)
        val summary2: Double = calculator.summary(Profit2, Penalty2)


        result = "\nSummary 1: %.2f тис. грн.\nSummary 2:  %.2f тис. грн.".format(summary1, summary2)
    }

    Column(modifier = Modifier.padding(60.dp, 140.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Calculator",
            modifier = Modifier.padding(0.dp, 20.dp)
        )
        TextField(
            value = P,
            onValueChange = { P = it },
            label = { Text("Enter the P value") },
            maxLines = 1,
        )
        TextField(
            value = B,
            onValueChange = { B = it },
            label = { Text("Enter the B value") },
            maxLines = 1,
        )
        TextField(
            value = σ1,
            onValueChange = { σ1 = it },
            label = { Text("Enter the σ1 value") },
            maxLines = 1,
        )
        TextField(
            value = σ2,
            onValueChange = { σ2 = it },
            label = { Text("Enter the σ2 value") },
            maxLines = 1,
        )
        Button(
            modifier = Modifier.padding(20.dp),
            onClick = { calculate() }
        ) {
            Text("Calculate")
        }
        if (result.isNotEmpty()) {
            Text("Result: $result")
        }
        Box(
            modifier = Modifier.padding(top = 100.dp)
        ) {
            Button(
                onClick = goBack
            ) {
                Text("Go back")
            }
        }
    }
}