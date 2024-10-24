
package com.example.apppersonas_adriansaavedra.ui.pantallaPersonas

import com.example.apppersonas_adriansaavedra.domain.modelo.Persona
import com.example.apppersonas_adriansaavedra.ui.common.UiEvent

data class PersonaState (
    val persona : Persona = Persona(),
    val siguiente: Boolean? =true,
    val anterior : Boolean? = true,
    val updatedelete : Boolean? = true,
    val indiceGrafica : Int = 0,
    val longitudLista: Int = 0,
    val aviso: UiEvent? = null
)