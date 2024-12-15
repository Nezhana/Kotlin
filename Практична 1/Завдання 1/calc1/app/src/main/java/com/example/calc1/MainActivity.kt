package com.example.calc1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        val Title: TextView = findViewById(R.id.Title)
        val Hp_Input: EditText = findViewById(R.id.Hp_Input)
        val Cp_Input: EditText = findViewById(R.id.Cp_Input)
        val Sp_Input: EditText = findViewById(R.id.Sp_Input)
        val Np_Input: EditText = findViewById(R.id.Np_Input)
        val Op_Input: EditText = findViewById(R.id.Op_Input)
        val Wp_Input: EditText = findViewById(R.id.Wp_Input)
        val Ap_Input: EditText = findViewById(R.id.Ap_Input)

        val SubmitBtn: Button = findViewById(R.id.SubmitBtn)

        SubmitBtn.setOnClickListener() {

//            Title.text = "Hello!"

            val Hp_Float = Hp_Input.text.toString().toFloat()
            val Cp_Float = Cp_Input.text.toString().toFloat()
            val Sp_Float = Sp_Input.text.toString().toFloat()
            val Np_Float = Np_Input.text.toString().toFloat()
            val Op_Float = Op_Input.text.toString().toFloat()
            val Wp_Float = Wp_Input.text.toString().toFloat()
            val Ap_Float = Ap_Input.text.toString().toFloat()


            val Krc = CalcKrc(Wp_Float)
            val Krg = CalcKrg(Wp_Float, Ap_Float)

            val Er = arrayOf(Hp_Float, Cp_Float, Sp_Float, Np_Float, Op_Float, Wp_Float, Ap_Float)
            //                4.20f,     62.1f,    3.30f,    1.20f,    6.40f,    7.00f,   15.80f

            val Ec = CalcEc(Krc, Er)
            // 4.516129 66.77419 3.5483873 1.2903227 6.881721 16.989248
            //    Hc      Cc        Sc        Nc        Oc       Ac

            val Eg = CalcEg(Krg, Er)
            // 5.4404144 80.440414 4.2746115 1.5544043 8.290156
            //    Hg        Cg        Sg        Ng        Og

            val Kn = arrayOf(339.0f, 1030.0f, 108.8f, 25.0f)

            val Qr = CalcQr(Er, Kn)
            val Qc = CalcQc(Qr, Krc, Er)
            val Qg = CalcQg(Qr, Krg, Er)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Krc", Krc)
            intent.putExtra("Krg", Krg)
            intent.putExtra("Ec", Ec.map{it.toString()}.reduce{resEc, nr -> "$resEc, $nr"})
            intent.putExtra("Eg", Eg.map{it.toString()}.reduce{resEg, nr -> "$resEg, $nr"})
            intent.putExtra("Qr", "$Qr кДж/кг  |  ${Qr/1000} МДЖ/кг")
            intent.putExtra("Qc", "$Qc кДж/кг  |  ${Qc/1000} МДЖ/кг")
            intent.putExtra("Qg", "$Qg кДж/кг  |  ${Qg/1000} МДЖ/кг")
            startActivity(intent)
        }

    }

    fun CalcKrc(Wp_Float:Float) :Float{
        val Krc  = 100 / (100 - Wp_Float)
        return Krc
    }

    fun CalcKrg(Wp_Float:Float, Ap_Float:Float) :Float{
        val Krg  = 100 / (100 - Wp_Float - Ap_Float)
        return Krg
    }

    fun CalcEc(Krc:Float, Er:Array<Float>) :Array<Float>{
        val Ec = Array(6, {1.0f})
        var i = 0
        while (i in Er.indices) {
            Ec[i] = Er[i] * Krc
            i++
            if (i == 5) {
                Ec[i] = Er[i+1] * Krc
                break
            }
        }
        return Ec
    }


    fun CalcEg(Krg:Float, Er:Array<Float>) :Array<Float>{
        val Eg = Array(6, {1.0f})
        var i = 0
        while (i in Er.indices) {
            Eg[i] = Er[i] * Krg
            i++
            if (i == 5) {
                break
            }
        }
        return Eg
    }

    fun CalcQr(Er:Array<Float>, Kn:Array<Float>) :Float{
        val Qr = (Kn[0] * Er[1]) + (Kn[1] * Er[0]) - Kn[2] * (Er[4] - Er[2]) - (Kn[3] * Er[5])
        return Qr
    }

    fun CalcQc(Qr:Float, Krc:Float, Er: Array<Float>) :Float{
        val Qc = (Qr + 0.025 * Er[5]) * Krc
        return Qc.toFloat()
    }

    fun CalcQg(Qr:Float, Krg: Float, Er: Array<Float>) :Float{
        val Qg = (Qr + 0.025 * Er[5]) * Krg
        return Qg.toFloat()
    }
}
