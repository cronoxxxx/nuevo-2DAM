package com.example.misegundoproyecto_adriansaavedra.domain.usecases.personas

import com.example.misegundoproyecto_adriansaavedra.data.Repository
import com.example.misegundoproyecto_adriansaavedra.domain.modelo.Persona

class UpdatePersonUseCase {

    operator fun invoke(persona: Persona, newPersona: Persona){
        Repository.updatePersona(persona, newPersona)
    }
}