package com.example.calc2

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
        val Hg_Input: EditText = findViewById(R.id.Hg_Input)
        val Cg_Input: EditText = findViewById(R.id.Cg_Input)
        val Sg_Input: EditText = findViewById(R.id.Sg_Input)
        val Ng_Input: EditText = findViewById(R.id.Ng_Input)
        val Og_Input: EditText = findViewById(R.id.Og_Input)
        val Wr_Input: EditText = findViewById(R.id.Wr_Input)
        val Ac_Input: EditText = findViewById(R.id.Ac_Input)
        val Qg_Input: EditText = findViewById(R.id.Qg_Input)
        val Vc_Input: EditText = findViewById(R.id.Vc_Input)

        val SubmitBtn: Button = findViewById(R.id.SubmitBtn)

        SubmitBtn.setOnClickListener() {

//            Title.text = "Hello!"

            val Hg_Float = Hg_Input.text.toString().toFloat()
            val Cg_Float = Cg_Input.text.toString().toFloat()
            val Sg_Float = Sg_Input.text.toString().toFloat()
            val Ng_Float = Ng_Input.text.toString().toFloat()
            val Og_Float = Og_Input.text.toString().toFloat()
            val Wr_Float = Wr_Input.text.toString().toFloat()
            val Ac_Float = Ac_Input.text.toString().toFloat()
            val Qg_Float = Qg_Input.text.toString().toFloat()
            val Vc_Float = Vc_Input.text.toString().toFloat()

            val Eg = arrayOf(Hg_Float, Cg_Float, Sg_Float, Ng_Float, Og_Float)
            //                11.20f,   85.50f,    2.5f,     0.0f,     0.80f
//          Wr = 2.0f   Ac = 0.15f      Qg = 40.40f     Vc = 333.3f

//          Additional example:
//             Hg        Cg          Sg       Ng       Og       Wr     Ac     Qg     Vc
//            1.83  	92.40 	    2.23  	 1.05	  2.49     1.79   0.21   38.6   329.4


            val Kcr = CalcKcr(Wr_Float)
            val Ar = CalcAr(Ac_Float, Kcr)

            val Kgr = CalcKgr(Wr_Float, Ar)
            val Er = CalcEr(Wr_Float, Ar, Kgr, Eg)

            val Qr = CalcQr(Qg_Float, Kgr, Wr_Float)
            val Vr = CalcVr(Vc_Float, Kcr)

            val intent = Intent(this, ResultActivity::class.java)
            val ErNames = arrayOf("Hr", "Cr", "Sr", "Nr", "Or", "Wr", "Ar")
            var i = 0
            while (i in Er.indices) {
                intent.putExtra(ErNames[i], Er[i])
                i++;
            }
            intent.putExtra("Qr", "${Qr} МДЖ/кг")
            intent.putExtra("Vr", "$Vr мг/кг")
            startActivity(intent)
        }

    }

    fun CalcKcr(Wr:Float) :Float{
        val Kcr = (100 - Wr) / 100
        return Kcr
    }

    fun CalcAr(Ac:Float, Kcr:Float) :Float{
        val Ar = Ac * Kcr
        return Ar
    }

    fun CalcKgr(Wr:Float, Ar:Float) :Float{
        val Kgr = (100 - Wr - Ar) / 100
        return Kgr
    }

    fun CalcEr(Wr:Float, Ar:Float, Kgr:Float, Eg:Array<Float>) :Array<Float>{
        val Er = Array<Float>(7, {1.0f})
        var i = 0
        while (i in Eg.indices) {
            Er[i] = Eg[i] * Kgr
            i++;
        }
        Er[5] = Wr
        Er[6] = Ar

        return Er
    }

    fun CalcQr(Qg:Float, Kgr:Float, Wr:Float) :Float{
        val Qr = Qg * Kgr - 0.025 * Wr
        return Qr.toFloat()
    }

    fun CalcVr(Vc:Float, Kcr:Float) :Float{
        val Vr = Vc * Kcr
        return Vr
    }

}
