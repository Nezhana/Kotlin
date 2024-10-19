fun main() {

    // k = (10**6 / Q) * a * (A / (100 - G)) * (1 - n) + kS
    // Для вугілля (робоча маса палива)
    //               Hr,     Cr,     Sr,    Nr,    Or,    Wr,     Ar
    // val Rr = arrayOf(3.50f, 52.49f, 2.85f, 0.97f, 4.99f, 10.00f, 25.20f)
    // val Vr = 25.92f
    // val Qr = 20.47f
    // var a = 0.80f // з таблиці (Відкрита топка з рідким шлаковидаленням)
    // val Wr = Rr[5]
    // val Ar = Rr[6]
    // var Gr = 100.0f / (100.0f - Wr - Ar)
    // val n = 0.985f
    // val kS = 0

    // val k = (Math.pow(10.0, 6.0) / Qr) * a * (Ar / (100.0f - Gr)) * (1 - n) + kS
    // println("k = $k (г/ГДж)")

    // // E = 10**(-6) * k * Qr * B
    // val B = 1096363
    // val E = Math.pow(10.0, (-6.0)) * k * Qr * B
    // println("E = $E (т.)")

    // Для мазута (горюча маса палива)
    //               Hg,     Cg,     Sg,    Ng,    Og,    Wr,     As
    // val Rg = arrayOf(11.20f, 85.50f, 2.50f, 0.80f, 0.80f, 2.00f, 0.15f)
    // val Vg = 333.3f
    // val Qg = 40.40f
    // var a = 1.0f // з таблиці (Відкрита топка з рідким шлаковидаленням)
    // val Wr = Rg[5]
    // val As = Rg[6]
    // val Krs = 100.0f / (100.0f - Wr)
    // val Ksg = 100.0f / (100.0f - As)
    // val Wg = (Wr * Krs) * Ksg
    // val Ag = As * Ksg
    // var Gg = 100.0f / (100.0f - Wg - Ag)
    // val n = 0.985f
    // val kS = 0
    // val Ar = (100.0f - Wr) / 100.0f
    // var Gr = 100.0f / (100.0f - Wr - Ar)
    // val B2 = 70945

    // val Qr2 = Qg * (100 - Wr - Ar) / 100 - 0.025 * Wr
    // println("Qr = $Qr2, Ar = $Ar")
    
    // val k2 = (Math.pow(10.0, 6.0) / Qr2) * a * (Ar / (100.0f - Gr)) * (1 - n) + kS
    // println("k = $k2 (г/ГДж)")
    
    // val E = Math.pow(10.0, (-6.0)) * k2 * Qr2 * B2
    // println("E = $E (т.)")
}

// work(r) => dry(c)
fun CalcKrc(Wp_Float:Float) :Float{
    val Krc  = 100 / (100 - Wp_Float)
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

fun Calc_k(Qr:Float, a:Float, Ar_Float:Float, Gr:Float, n:Float, kS:Float) :FLoat{
    // k = (10**6 / Qr) * a * (Ar / (100 - Gr)) * (1 - n) + kS
    val k = (Math.pow(10.0f, 6.0f) / Qr) * a * (Ar_Float / (100 - Gr)) * (1 - n) + kS
    return k
}

fun Calc_E(Qr:Float, k:Float, B:Float) :Float{
    val E = Math.pow(10.0f, -6.0f) * k * Qr * B
    return E
}

fun Calc_Qcr(Wr:Float) :Float{
    val Qr = (100.0f - Wr) / 100.0f
    return Qr
}

fun Calc_Qgr() :Float{
    val Qr = (100.0f - Wr - Ar) / 100.0f
    return Qr
}