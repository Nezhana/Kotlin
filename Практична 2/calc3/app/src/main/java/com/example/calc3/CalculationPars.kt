package com.example.calc3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CalculationPars : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.calculating)

        val option = intent.getIntExtra("option", 1)

        val foilName: TextView = findViewById(R.id.foil_name)
        val e1: TextView = findViewById(R.id.E1)
        val e2: TextView = findViewById(R.id.E2)
        val e3: TextView = findViewById(R.id.E3)
        val e4: TextView = findViewById(R.id.E4)
        val e5: TextView = findViewById(R.id.E5)
        val e6: TextView = findViewById(R.id.E6)
        val e7: TextView = findViewById(R.id.E7)
        val e8: TextView = findViewById(R.id.E8)

        // Мазут
        if (option == 2) {
            foilName.text = "для мазуту"
            e1.text = "H"
            e2.text = "C"
            e3.text = "S"
            e4.text = "N"
            e5.text = "O"
            e6.text = "As"
            e7.text = "Wr"
            e8.text = "V"
        } else if (option == 3) {
            foilName.text = "для природнього газу"
            e1.text = "CH4"
            e2.text = "C2H6"
            e3.text = "C3H8"
            e4.text = "C4H10"
            e5.text = "CO2"
            e6.text = "N2"
            e7.text = "ρ"
            e8.text = "X"
        } else {
            foilName.text = "для вугілля"
        }

        val e1_inp: EditText = findViewById(R.id.E1_input)
        val e2_inp: EditText = findViewById(R.id.E2_Input)
        val e3_inp: EditText = findViewById(R.id.E3_input)
        val e4_inp: EditText = findViewById(R.id.E4_input)
        val e5_inp: EditText = findViewById(R.id.E5_input)
        val e6_inp: EditText = findViewById(R.id.E6_input)
        val e7_inp: EditText = findViewById(R.id.E7_input)
        val e8_inp: EditText = findViewById(R.id.E8_input)

        val b_inp: EditText = findViewById(R.id.B_inp)
        val q_inp: EditText = findViewById(R.id.Q_inp)
        val n_inp: EditText = findViewById(R.id.n_inp)

        val SubmBtn: Button = findViewById(R.id.subm_btn)

        val BackBtn: ImageButton = findViewById(R.id.backBtn)

        BackBtn.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        SubmBtn.setOnClickListener() {
            val C_float = e1_inp.text.toString().toFloat()
            val H_float = e2_inp.text.toString().toFloat()
            val O_float = e3_inp.text.toString().toFloat()
            val N_float = e4_inp.text.toString().toFloat()
            val S_float = e5_inp.text.toString().toFloat()
            val A_float = e6_inp.text.toString().toFloat()
            val W_float = e7_inp.text.toString().toFloat()
            val Vr_float = e8_inp.text.toString().toFloat()

            val Q_float = q_inp.text.toString().toFloat()
            val B_float = b_inp.text.toString().toFloat()
            val n_float = n_inp.text.toString().toFloat()

            var a: Float
            var kS: Float
            var Gvin: Float
            var Qr: Float
            var Ar: Float
            var Krc: Float


            if (option == 2) {
                a = 1.0f
                kS = 0.0f
                Gvin = 0.0f
                Krc = CalcKrc(W_float)
                Ar = A_float * Krc
                Qr = Calc_Qgr(W_float, Ar)
            } else if (option == 3) {
                a = 0.0f
                kS = 0.0f
                Gvin = 0.0f
                Ar = 0.0f
                Qr = Q_float
            } else {
                a = 0.80f
                kS = 0.0f
                Gvin = 1.5f
                Qr = Q_float
                Ar = A_float
            }

            val k = Calc_k(Qr, a, Ar, Gvin, n_float, kS)
            e1.text = k.toString()
            val E = Calc_E(Qr, k, B_float)


            val intent = Intent(this, Results::class.java)
            intent.putExtra("k", k)
            intent.putExtra("E", "$E т.")
            intent.putExtra("option", option)
            startActivity(intent)

        }
    }

    fun CalcKrc(Wp_Float:Float) :Float{
        val Krc  = (100 - Wp_Float) / 100
        return Krc
    }

    // work(r) => daf(g)
    fun CalcKrg(Wp_Float:Float, Ap_Float:Float) :Float{
        val Krg  = 100 / (100 - Wp_Float - Ap_Float)
        return Krg
    }

    // dry(c) => daf(g)
    fun CalcKcg(Ac_Float:Float) :Float{
        val Kcg  = 100 / (100.0f - Ac_Float)
        return Kcg
    }

    fun Calc_k(Qr:Float, a:Float, Ar_Float:Float, Gvin:Float, n:Float, kS:Float) :Float{
        // k = (10**6 / Qr) * a * (Ar / (100 - Gr)) * (1 - n) + kS
        val k = (Math.pow(10.0, 6.0).toFloat() / Qr) * a * (Ar_Float / (100 - Gvin)) * (1 - n) + kS
        return k
    }

    fun Calc_E(Qr:Float, k:Float, B:Float) :Float{
        val E = Math.pow(10.0, -6.0).toFloat() * k * Qr * B
        return E
    }

    fun Calc_Qcr(Wr:Float) :Float{
        val Qr = (100.0f - Wr) / 100.0f
        return Qr
    }

    fun Calc_Qgr(Wr:Float, Ar:Float) :Float{
        val Qr = (100.0f - Wr - Ar) / 100.0f
        return Qr
    }

}