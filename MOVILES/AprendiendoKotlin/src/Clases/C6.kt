package Clases

fun main() {
    val diasSemana = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    for (dia in diasSemana) {
        println(dia)
    }

    diasSemana.set(2,"Marte")
println("---------------------------")
   diasSemana.forEach {
       println(it)
   }
println("---------------------------")
    if (diasSemana.size > 5) {
      println(diasSemana[diasSemana.size - 1])
    }
    println("---------------------------")
    println(diasSemana.first())
}