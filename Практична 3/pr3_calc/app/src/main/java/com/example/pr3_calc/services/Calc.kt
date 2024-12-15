package com.example.pr3_calc.services

import org.apache.commons.math3.special.Erf
import kotlin.math.sqrt
import kotlin.math.exp

class Calc {
    private val σ1: Double // = 1.00f
    private val σ2: Double // = 0.25f
    private val P1: Double // = 5.25f
    private val P2: Double // = 4.75f

    public constructor(σ1: Double, σ2: Double, P1: Double, P2: Double) {
        this.σ1 = σ1
        this.σ2 = σ2
        this.P1 = P1
        this.P2 = P2
    }


    fun F(p:Double, σ:Double, P:Double) :Double{
        val F_p  = (Erf.erf((p-P)/(sqrt(2.0f)*σ)))/2
        return F_p
    }

    fun δ_w(p1:Double, p2:Double) :Double{
        val δ = p1 - p2
        return δ
    }

    fun W(δ:Double, B:Double) :Double{
        val W = δ * 24 * B
        return W
    }

    fun P(W:Double, B:Double) :Double{
        val P = W * B
        return P
    }

    fun summary(profit:Double, penalty:Double) :Double{
        val summ = profit - penalty
        return summ
    }
}