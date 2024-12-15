package com.example.pr4_calc.services

import java.math.RoundingMode
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.sqrt

class Calc {

    // CONST VALUES
    private val S_nom_t = 6.3
    private val U_s_nom = 10.5
    private val U_v_nom = 115.0
    private val TMH_6300_110_Uk = 10.5
    private val TMH_6300_110_U_n_n = 11.0




    // PART 1:
    fun I_normal(S_norm:Double, U:Double) :Double{
        val I_norm = (S_norm / 2) / (sqrt(3.0f) * U)
        return I_norm
    }

    fun I_afterAccident(I_normal:Double) :Double{
        val I_aftrAcc = 2.0f * I_normal
        return I_aftrAcc
    }

    fun get_j_ek(isolation:String, material:String, T_norm:Int) :Double { //used inside
        val j_ek = when (isolation) {
                    "non-isolated" -> when (material) {
                        "copper" -> when {
                            T_norm < 3000 -> 2.5f
                            T_norm < 5000 -> 2.1f
                            else -> 1.8f

                        }

                        "aluminium" -> when {
                            T_norm < 3000 -> 1.3f
                            T_norm < 5000 -> 1.1f
                            else -> 1.0f
                        }

                        else -> 1.3f
                    }

                    "paper" -> when (material) {
                        "copper" -> when {
                            T_norm < 3000 -> 3.0f
                            T_norm < 5000 -> 2.5f
                            else -> 2.0f

                        }

                        "aluminium" -> when {
                            T_norm < 3000 -> 1.6f
                            T_norm < 5000 -> 1.4f
                            else -> 1.2f
                        }

                        else -> 1.6f
                    }

                    else -> when (material) {
                        "copper" -> when {
                            T_norm < 3000 -> 3.5f
                            T_norm < 5000 -> 3.1f
                            else -> 2.7f

                        }

                        "aluminium" -> when {
                            T_norm < 3000 -> 1.9f
                            T_norm < 5000 -> 1.7f
                            else -> 1.6f
                        }

                        else -> 1.9f
                    }
                }
        return j_ek.toDouble()
    }

    fun get_Ct_termal(isolation: String) :Int{ //used inside
        val Ct = when(isolation) {
                    "paper" -> 92
                    "plastic" -> 75
                    else -> 65
                }
        return Ct
    }

    fun s_ek_and_min (I_normal: Double, I:Double, tf:Double, isolation: String, material: String, T_norm: Int): Array<Double>{
        val j_ek = this.get_j_ek(isolation, material, T_norm)
        val Ct = this.get_Ct_termal(isolation)
        val s_ek = I_normal/j_ek
        val s_min = ((I*1000)*sqrt(tf))/Ct
        var s_ek_and_min = arrayOf(s_ek, s_min)
        return s_ek_and_min
    }

    fun get_s (s_min:Double) :Int{
        return s_min.toBigDecimal().setScale(-1, RoundingMode.UP).toInt()
    }

    // PART 2:
    fun Xc_v1(Sk: Double) :Double{ //used inside
        val Xc = this.U_s_nom.pow(2) / Sk
        return Xc
    }

    fun Xt_v1() :Double{ //used inside
        val Xt = (this.U_s_nom / 100) * (this.U_s_nom.pow(2) / this.S_nom_t)
        return Xt
    }

    fun X_sum_v1(Sk: Double) :Double { //used inside
        val X_sum = this.Xc_v1(Sk) + this.Xt_v1()
        return X_sum
    }

    fun I0(Sk: Double) :Double{
        val I0 = this.U_s_nom/(sqrt(3.0) * this.X_sum_v1(Sk))
        return I0
    }

    // PART 3:
    fun Xt_v2() :Double{ //used inside
        val Xt = (this.TMH_6300_110_Uk * this.U_v_nom.pow(2)) / (100 * this.S_nom_t)
        return Xt
    }

    fun bus_resistance(Xc:Double, Rc:Double) :Array<Double>{ //also formula for min
        val X_sh = Xc + this.Xt_v2()
        val R_sh = Rc
        val Z_sh = sum_resistance(X_sh, R_sh)
        return arrayOf(X_sh, R_sh, Z_sh)
    }

    fun sum_resistance(X: Double, R:Double) :Double { //used inside
        val Z = sqrt(X.pow(2)+R.pow(2))
        return Z
    }

    fun bus_current(Z: Double) :Array<Double>{ //also formula for min
        val I_3 = (this.U_v_nom*10.0.pow(3))/(sqrt(3.0)*Z)
        val I_2 = I_3 * (sqrt(3.0)/2)
        return arrayOf(I_3, I_2)
    }

    fun resistance_k() :Double{
        val k = this.TMH_6300_110_U_n_n.pow(2)/this.U_v_nom.pow(2)
        return k
    }

    fun get_wire_resistance(s:Int, l_s:Double) :Array<Double>{
        val R = when(s) {
                    16 -> 1.8007
                    25 -> 1.1498
                    35 -> 0.8347
                    50 -> 0.5784
                    70 -> 0.4131
                    95 -> 0.3114
                    120 -> 0.2459
                    else -> 0.8347
                }
        val X = when {
                    l_s in 0.8..0.8999 -> 0.341
                    l_s in 1.0..1.249 -> 0.355
                    l_s in 1.25..1.499 -> 0.369
                    l_s in 1.5..1.999 -> 0.380
                    l_s in 2.0..2.499 -> 0.398
                    l_s in 2.5..2.999 -> 0.413
                    l_s in 3.0..3.499 -> 0.423
                    else -> 0.433
                }
        return arrayOf(R, X)
    }

    fun bus_normalized_current(Z: Double) :Array<Double>{ //also formula for min
        val I_3 = (this.TMH_6300_110_U_n_n*10.0.pow(3))/(sqrt(3.0)*Z)
        val I_2 = I_3 * (sqrt(3.0)/2)
        return arrayOf(I_3, I_2)
    }
}