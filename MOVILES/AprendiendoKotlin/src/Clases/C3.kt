package Clases

fun main() {
    //funciones
    cadena()
    println(sumar(10, 20))
    println(sumar(20, 30))
    var prueba = sumar(papaya = 20)
    println(prueba)
    println(sumar(papaya = 30))
}


fun sumar(a: Int = 0, papaya: Int): Int {
    return a + papaya
}

fun cadena(){
    println("hola")
}