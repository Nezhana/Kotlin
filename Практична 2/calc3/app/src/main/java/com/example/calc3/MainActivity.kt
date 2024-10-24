package com.example.calc3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.calc3.CalculationPars
import com.example.calc3.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val Option1: Button = findViewById(R.id.btnOption1)
        val Option2: Button = findViewById(R.id.btnOption2)
        val Option3: Button = findViewById(R.id.btnOption3)

        Option1.setOnClickListener() {
            val intent = Intent(this, CalculationPars::class.java)
            intent.putExtra("option", 1)
            startActivity(intent)
        }

        Option2.setOnClickListener() {
            val intent = Intent(this, CalculationPars::class.java)
            intent.putExtra("option", 2)
            startActivity(intent)
        }

        Option3.setOnClickListener() {
            val intent = Intent(this, CalculationPars::class.java)
            intent.putExtra("option", 3)
            startActivity(intent)
        }

    }
}