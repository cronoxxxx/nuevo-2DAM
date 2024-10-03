package com.example.misegundoproyecto_adriansaavedra.ui.pantallaMain

import com.example.misegundoproyecto_adriansaavedra.domain.modelo.Persona

data class MainState (
    val persona : Persona = Persona(),
    val aviso : String? = null,
    val siguiente: Boolean? =true,
    val anterior : Boolean? = true,
    val updatedelete : Boolean? = true
)