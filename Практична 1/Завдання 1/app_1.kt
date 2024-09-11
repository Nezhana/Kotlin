

fun main() {

    val Er = arrayOf(4.20f, 62.1f, 3.30f, 1.20f, 6.40f, 7.00f, 15.80f)
    //                Hr     Cr     Sr     Nr     Or     Wr      Ar

    // for (el in Er) {
    //     print("$el ")
    // }

    val Krc = 100 / (100 - Er[5])
    // println("$Krc")
    val Krg = 100 / (100 - Er[5] - Er[6])
    // println("$Krg")

    var Ec = Array(6, {1.0f})
    var i = 0
    while (i in Er.indices) {
        Ec[i] = Er[i] * Krc
        i++
        if (i == 5) {
        	Ec[i] = Er[i+1] * Krc
            break
        };
    }

    for (el in Ec) {
        print("$el ")
    }
    // 4.516129 66.77419 3.5483873 1.2903227 6.881721 16.989248
    //    Hc      Cc        Sc        Nc        Oc       Ac

    println()
        
    var Eg = Array(5, {1.0f})
    i = 0
    while (i in Er.indices) {
        Eg[i] = Er[i] * Krg
        i++
        if (i == 5) {
            break
        };
    }

    for (el in Eg) {
        print("$el ")
    }
    // 5.4404144 80.440414 4.2746115 1.5544043 8.290156 
    //    Hg        Cg        Sg        Ng        Og 

    println()

    val Kn = arrayOf(339.0f, 1030.0f, 108.8f, 25.0f)
    val Qr = (Kn[0] * Er[1]) + (Kn[1] * Er[0]) - Kn[2] * (Er[4] - Er[2]) - (Kn[3] * Er[5])
    println("Qр: $Qr кДж/кг  |  ${Qr/1000} МДЖ/кг")

    val Qc = (Qr + 0.025 * Er[5]) * Krc
    println("Qс: $Qc кДж/кг  |  ${Qc/1000} МДЖ/кг")
    
    val Qg = (Qr + 0.025 * Er[5]) * Krg
    println("Qг: $Qg кДж/кг  |  ${Qg/1000} МДЖ/кг")

}
