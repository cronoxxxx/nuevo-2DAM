package com.example.misegundoproyecto_adriansaavedra.ui.pantallaMain

import com.example.misegundoproyecto_adriansaavedra.domain.modelo.Persona

data class MainState (
    val persona : Persona = Persona(),
val error : String? = null,
val nosiguiente: Boolean? =true,
val noanterior : Boolean? = true,
val update_delete : Boolean? = true
)