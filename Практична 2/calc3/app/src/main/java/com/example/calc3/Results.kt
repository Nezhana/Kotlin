package com.example.calc3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calc3.CalculationPars
import com.example.calc3.R

class Results : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.results)

        val k = intent.getFloatExtra("k", 0.0f)
        val E = intent.getStringExtra("E")
        val option = intent.getIntExtra("option", 1)

        val k_value: TextView = findViewById(R.id.k_value)
        k_value.text = "$k г/ГДж"
        val E_value: TextView = findViewById(R.id.E_value)
        E_value.text = E.toString()

        val BackBtn: ImageButton = findViewById(R.id.backBtn)

        BackBtn.setOnClickListener() {
            val intent = Intent(this, CalculationPars::class.java)
            intent.putExtra("option", option)
            startActivity(intent)
        }

    }
}