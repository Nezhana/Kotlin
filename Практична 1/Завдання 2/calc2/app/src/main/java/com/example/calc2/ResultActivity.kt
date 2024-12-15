package com.example.calc2

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.done_calculating)

//        "Hr, Cr, Sr, Nr, Or, Wr, Ar"
        val hr = intent.getFloatExtra("Hr", 0.0f)
        val cr = intent.getFloatExtra("Cr", 0.0f)
        val sr = intent.getFloatExtra("Sr", 0.0f)
        val nr = intent.getFloatExtra("Nr", 0.0f)
        val or = intent.getFloatExtra("Or", 0.0f)
        val wr = intent.getFloatExtra("Wr", 0.0f)
        val ar = intent.getFloatExtra("Ar", 0.0f)
        val qr = intent.getStringExtra("Qr")
        val vr = intent.getStringExtra("Vr")

        val hrValue: TextView = findViewById(R.id.Hr)
        hrValue.text = "Hr $hr %"
        val crValue: TextView = findViewById(R.id.Cr)
        crValue.text = "Cr $cr %"
        val srValue: TextView = findViewById(R.id.Sr)
        srValue.text = "Sr $sr %"
        val nrValue: TextView = findViewById(R.id.Nr)
        nrValue.text = "Nr $nr %"
        val orValue: TextView = findViewById(R.id.Or)
        orValue.text = "Or $or %"
        val wrValue: TextView = findViewById(R.id.Wr)
        wrValue.text = "Wr $wr %"
        val arValue: TextView = findViewById(R.id.Ar)
        arValue.text = "Ar $ar %"

        val Qr: TextView = findViewById(R.id.qr_value)
        Qr.text = qr.toString()

        val Vr: TextView = findViewById(R.id.vr_value)
        Vr.text = vr.toString()

        val backBtn: ImageButton = findViewById(R.id.backBtn)

        backBtn.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}