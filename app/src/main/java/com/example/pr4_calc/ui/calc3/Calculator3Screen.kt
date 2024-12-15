package com.example.pr4_calc.ui.calc3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr4_calc.services.Calc
import java.lang.invoke.MethodHandles.loop


@Composable
fun Calculator3Screen(
    goBack: () -> Unit,
    calculator: Calc
) {
    var R_norm by remember { mutableStateOf("") }
    var X_norm by remember { mutableStateOf("") }
    var R_min by remember { mutableStateOf("") }
    var X_min by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculate(): Unit {

        val double_R_norm = R_norm.toDouble()
        val double_X_norm = X_norm.toDouble()
        val double_R_min = R_min.toDouble()
        val double_X_min = X_min.toDouble()

        val (X_sh, R_sh, Z_sh) = calculator.bus_resistance(double_X_norm, double_R_norm)
        val (X_sh_min, R_sh_min, Z_sh_min) = calculator.bus_resistance(double_X_min, double_R_min)
        val (I_3, I_2) = calculator.bus_current(Z_sh)
        val (I_3_min, I_2_min) = calculator.bus_current(Z_sh_min)

        val values = listOf(X_sh, R_sh, Z_sh, X_sh_min, R_sh_min, Z_sh_min)
        //       (X_sh_n, R_sh_n, Z_sh_n, X_sh_n_min, R_sh_n_min, Z_sh_n_min)
        val newValues = mutableListOf<Double>()


        val k = calculator.resistance_k()
        val iterator = (0..5).iterator()
        iterator.forEach {
            newValues.add(values[it] * k)
        }

        val (Z_n, Z_n_min) = arrayOf(newValues[2], newValues[5])
        val (I_3_n, I_2_n) = calculator.bus_normalized_current(Z_n)
        val (I_3_n_min, I_2_n_min) = calculator.bus_normalized_current(Z_n_min)

        // Lambda
        val multiplyArray: (Array<Double>, Double) -> Array<Double> = { arr, num ->
            arr.map { it * num }.toTypedArray()
        }

        // A-50 => s = 50 мм^2 | l_s = 12.37 / 8 = 1.54625
        // and then R_l = R0 * l | X_l = X0 * l | l = 12.37
        val (R_l, X_l) = multiplyArray(calculator.get_wire_resistance(50, 1.54625), 12.37)
        val Z_sum = calculator.sum_resistance((X_l + newValues[0]), (R_l + newValues[1]))
        val Z_min_sum = calculator.sum_resistance((X_l + newValues[3]), (R_l + newValues[4]))

        val (I_3_l, I_2_l) = calculator.bus_normalized_current(Z_sum)
        val (I_3_l_min, I_2_l_min) = calculator.bus_normalized_current(Z_min_sum)

        result = "\nI3л: $I_3_l\nI2л: $I_2_l\nI3л min: $I_3_l_min\nI2л min: $I_2_l_min"
    }

    Column(modifier = Modifier.padding(60.dp, 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Calculator",
            modifier = Modifier.padding(0.dp, 20.dp)
        )
        TextField(
            value = R_norm,
            onValueChange = { R_norm = it },
            label = { Text("Enter the R norm value") },
            maxLines = 1,
        )
        TextField(
            value = X_norm,
            onValueChange = { X_norm = it },
            label = { Text("Enter the X norm value") },
            maxLines = 1,
        )
        TextField(
            value = R_min,
            onValueChange = { R_min = it },
            label = { Text("Enter the R min value") },
            maxLines = 1,
        )
        TextField(
            value = X_min,
            onValueChange = { X_min = it },
            label = { Text("Enter the X min value") },
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
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Button(
                onClick = goBack
            ) {
                Text("Go back")
            }
        }
    }
}