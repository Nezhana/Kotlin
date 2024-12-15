package com.example.pr4_calc.ui.calc1

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pr4_calc.R
import com.example.pr4_calc.ui.dropdown.DropdownList


@Composable
fun Calculator1Screen(
    goBack: () -> Unit,
    goNext: () -> Unit,
    calculator: Calc
) {
    var U by remember { mutableStateOf("") }
    var I_k by remember { mutableStateOf("") }
    var t_f by remember { mutableStateOf("") }
    var S_m by remember { mutableStateOf("") }
    var T_m by remember { mutableStateOf("") }

    var isolation: List<String> = listOf("non-isolated", "paper", "plastic", "rubber")
    var material: List<String> = listOf("aluminium", "copper")
    var selectedIndexDrop1 by rememberSaveable { mutableStateOf(0) }
    var selectedIndexDrop2 by rememberSaveable { mutableStateOf(0) }
    val buttonModifier = Modifier.width(280.dp)

    val images = mapOf(
        "non-isolated-aluminium" to R.drawable.non_isolated_aluminium,
        "non-isolated-copper" to R.drawable.non_isolated_copper,
        "paper-aluminium" to R.drawable.paper_aluminium,
        "paper-copper" to R.drawable.paper_copper,
        "plastic-aluminium" to R.drawable.plastic_aluminium,
        "plastic-copper" to R.drawable.plastic_copper,
        "rubber-aluminium" to R.drawable.rubber_aluminium,
        "rubber-copper" to R.drawable.rubber_copper)
    var selectedImg by remember { mutableStateOf(images["non-isolated-aluminium"]) }

    var result by remember { mutableStateOf("") }

    fun calculate(): Unit {

        val double_U = U.toDouble()
        val double_I_k = I_k.toDouble()
        val double_t_f = t_f.toDouble()
        val double_S_m = S_m.toDouble()
        val int_T_m = T_m.toInt()

        val I_norm = calculator.I_normal(double_S_m, double_U)
        val I_aftrAcc = calculator.I_afterAccident(I_norm)
        val (s_ek, s_min) = calculator.s_ek_and_min(
                                        I_norm,
                                        double_I_k,
                                        double_t_f,
                                        isolation[selectedIndexDrop1],
                                        material[selectedIndexDrop2],
                                        int_T_m)
        val s = calculator.get_s(s_min)

        result = "Summary: $s"
    }

    Column(modifier = Modifier.padding(60.dp, 100.dp),
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
            value = I_k,
            onValueChange = { I_k = it },
            label = { Text("Enter the Ik value") },
            maxLines = 1,
        )
        TextField(
            value = t_f,
            onValueChange = { t_f = it },
            label = { Text("Enter the tф value") },
            maxLines = 1,
        )
        TextField(
            value = S_m,
            onValueChange = { S_m = it },
            label = { Text("Enter the Sм value") },
            maxLines = 1,
        )
        TextField(
            value = T_m,
            onValueChange = { T_m = it },
            label = { Text("Enter the Tм value") },
            maxLines = 1,
        )
        DropdownList(isolation, selectedIndexDrop1, buttonModifier, onItemClick = {
            selectedIndexDrop1 = it
            val (isolation_ind, material_ind) = arrayOf(isolation[it], material[selectedIndexDrop2])
            selectedImg = images["$isolation_ind-$material_ind"]
        })
        DropdownList(material, selectedIndexDrop2, buttonModifier, onItemClick = {
            selectedIndexDrop2 = it
            val (isolation_ind, material_ind) = arrayOf(isolation[selectedIndexDrop1], material[it])
            selectedImg = images["$isolation_ind-$material_ind"]
        })
        Image(
            painter = painterResource(selectedImg!!),
            contentDescription = "cable"
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
            modifier = Modifier.padding(top = 10.dp)
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
