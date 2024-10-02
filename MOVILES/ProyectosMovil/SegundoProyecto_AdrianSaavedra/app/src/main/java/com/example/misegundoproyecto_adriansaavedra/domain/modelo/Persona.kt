package com.example.misegundoproyecto_adriansaavedra.domain.modelo

import java.time.LocalDate


data class Persona(
    val nombre: String = "",
    val email: String = "",
    val estatura : Int = 0,
    val clave : Int = 0,
    val fechaNacimiento : String = "",
    val genero : String = "",
    val aceptarTerminos : Boolean = false

) {
    override fun toString(): String {
        return """
            Nombre: $nombre
            Email: $email
            Estatura: $estatura
            Clave: $clave
            FechaNacimiento: $fechaNacimiento
            Genero: $genero
            AceptarTerminos: $aceptarTerminos
        """.trimIndent()
    }
}
