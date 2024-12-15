package com.example.pr4_calc.ui.calc2


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement

//Calculator Class
import com.example.pr4_calc.services.Calc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun Calculator2Screen(
    goBack: () -> Unit,
    goNext: () -> Unit,
    calculator: Calc
) {
    var U by remember { mutableStateOf("") }
    var S_k by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculate(): Unit {

        val double_U = U.toDouble()
        val double_S_k = S_k.toDouble()

        val I0 = calculator.I0(double_S_k)

        result = "I: $I0 А"
    }

    Column(modifier = Modifier.padding(60.dp, 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Calculator",
            modifier = Modifier.padding(0.dp, 20.dp)
        )
        TextField(
            value = U,
            onValueChange = { U = it },
            label = { Text("Enter the U value") },
            maxLines = 1,
        )
        TextField(
            value = S_k,
            onValueChange = { S_k = it },
            label = { Text("Enter the S_k value") },
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
        Row(
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Button(
                onClick = goBack
            ) {
                Text("Go back")
            }
            Button(
                onClick = goNext
            ) {
                Text("Go next")
            }
        }
    }
}
