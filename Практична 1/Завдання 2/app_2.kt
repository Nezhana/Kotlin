
fun main() {

    // Hg, Cg, Sg, Ng, Og, Wr, Ac, Qg, Vc
    val Eg = arrayOf(11.20f, 85.50f, 2.5f, 0.0f, 0.80f)
    val Wr = 2.0f
    val Ac = 0.15f
    val Qg = 40.40f
    val Vc = 333.3f

    val Kcr = (100 - Wr) / 100
    val Ar = Ac * Kcr

    val Kgr = (100 - Wr - Ar) / 100
    var Er = Array(7, {1.0f})
    var i = 0
    while (i in Eg.indices) {
        Er[i] = Eg[i] * Kgr
        i++;
    }
    Er[5] = Wr
    Er[6] = Ar

    val Qr = Qg * Kgr - 0.025 * Wr

    val Vr = Vc * Kcr

    print("R: ")
    for (el in Er) {
        print("$el, ")
    }
    
    println("\nQr: $Qr МДж/кг\nVr: $Vr")

}
