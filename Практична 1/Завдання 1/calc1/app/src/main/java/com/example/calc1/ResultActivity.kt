package com.example.calc1

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.done_calculating)

//        val intent = Intent(this, MainActivity::class.java)

        val krc = intent.getFloatExtra("Krc", 0.0f)
        val krg = intent.getFloatExtra("Krg", 0.0f)
        val ec = intent.getStringExtra("Ec")
        val eg = intent.getStringExtra("Eg")
        val qr = intent.getStringExtra("Qr")
        val qc = intent.getStringExtra("Qc")
        val qg = intent.getStringExtra("Qg")


        val krcValue: TextView = findViewById(R.id.krc_value)
        krcValue.text = krc.toString()

        val krgValue: TextView = findViewById(R.id.krg_value)
        krgValue.text = krg.toString()

        val Ec: TextView = findViewById(R.id.Ec)
        Ec.text = ec.toString()

        val Eg: TextView = findViewById(R.id.Eg)
        Eg.text = eg.toString()

        val Qr: TextView = findViewById(R.id.qr_value)
        Qr.text = qr.toString()

        val Qc: TextView = findViewById(R.id.qc_value)
        Qc.text = qc.toString()

        val Qg: TextView = findViewById(R.id.qg_value)
        Qg.text = qg.toString()

        val backBtn: ImageButton = findViewById(R.id.backBtn)

        backBtn.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}