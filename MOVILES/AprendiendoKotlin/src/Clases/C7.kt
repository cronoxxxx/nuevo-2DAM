package Clases

fun main() {
    val diasSemana:MutableList<Any> = mutableListOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")

    diasSemana.add(1)
    diasSemana.add(true)

println("---------------------------")
    diasSemana.forEach {
        println(it)
    }

    diasSemana.add(0,"Datos: ")
    diasSemana.set(8,"ejemplo de cambio")
    println("---------------------")

    diasSemana.forEach {
        println(it)
    }

    println("----------------")

    diasSemana.filter {
        it == "Lunes"
    }.forEach {
        println(it)
    }

    val result = diasSemana.filter { it == "Lunes" || (it is String && it.startsWith("M")) }
    println(result)

}