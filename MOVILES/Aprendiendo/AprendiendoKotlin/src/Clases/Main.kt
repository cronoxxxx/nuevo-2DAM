package Clases

import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
  var a = 1
  var b = 2

    a=3
    b=4
    print(a + b)

    //Numbers
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val bytes = 0b11010010
    val bytes2 = 0xff
    val bytes3 = 0b0001_0010_0100_1000
    val pi = 3.14
    val e = 2.71828

    var edad: Int = 0
    edad = 10
    edad = 25.6.toInt()

    var otra:Long = 0
    otra = 10
    otra = 25.6.toLong()
    println(otra)


    var flotante:Float = 0.0f
    flotante = 25.6f
    println(flotante)


    var doble:Double = 0.0
    doble = 25.6
    println(doble)


    var x:Char = 'a'
    x = 'b'
    println(x)


    var bito:Byte = 0
    bito = 10
    println(bito)


    var bool :Boolean = true
    bool = false
    println(bool)


    var cadena:String = "hola"
    cadena = "adios"
    println(cadena)


    var hoy = Date()
    println(hoy)


    var otraVar = 10

    otraVar = 'a'.code //Asociamos el valor 97 al 'a'
    println(otraVar)

}