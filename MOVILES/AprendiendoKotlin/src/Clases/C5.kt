package Clases

fun main() {

   for (i in 1..12) {
       println(queMesEs(i))
   }

   println("-------------")
   for (i in 1..10) {
       println(queTrimestreEs(i))
   }

    println(queTrimestreEs(15).uppercase())

    var variable = 15
    println(queTipoVariableEs(variable))



}

fun queMesEs (mes: Int): String {
    return when (mes) {
        1 -> "Enero"
        2 -> "Febrero"
        3 -> "Marzo"
        4 -> "Abril"
        5 -> "Mayo"
        6 -> "Junio"
        7 -> "Julio"
        8 -> "Agosto"
        9 -> "Septiembre"
        10 -> "Octubre"
        11 -> "Noviembre"
        12 -> "Diciembre"
        else -> {"Nombre incorrecto"}
    }
}

fun queTrimestreEs(tipe: Int): String {
    return when (tipe) {
        1,2,3 -> "Primer trimestre"
        in 4..6 -> "Segundo trimestre"
        in 7..9 -> "Tercer trimestre"
        !in 10..12 -> "Te has ido"
        else -> {"LEJISIMOS"}}
}

fun queTipoVariableEs(variable: Any): String {
    return when (variable) {
        is Int -> "Entero"
        is Double -> "Decimal"
        is String -> "Cadena"
        else -> "No lo sé"
    }
}
