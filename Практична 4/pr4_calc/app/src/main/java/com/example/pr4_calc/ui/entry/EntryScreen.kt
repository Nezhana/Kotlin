package com.example.pr4_calc.ui.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val buttonStyles = Modifier.padding(all = 30.dp)

@Composable
fun EntryScreen(
    onCalculator1Navigate: () -> Unit,
    onCalculator2Navigate: () -> Unit,
    onCalculator3Navigate: () -> Unit,
    ) {
    Column(modifier = buttonStyles) {
        Button(
            onClick = { onCalculator1Navigate() },
        ) {
            Text(
                text = "Go to Part 1 calculations"
            )
        }
        Button(
            onClick = onCalculator2Navigate
        ) {
            Text(
                text = "Go to Part 2 calculations"
            )
        }
        Button(
            onClick = onCalculator3Navigate
        ) {
            Text(
                text = "Go to Part 3 calculations"
            )
        }
    }

}