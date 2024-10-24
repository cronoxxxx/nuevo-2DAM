package com.example.apppersonas_adriansaavedra.domain.usecases.personas

import com.example.apppersonas_adriansaavedra.data.Repository
import com.example.apppersonas_adriansaavedra.domain.modelo.Persona

class UpdatePersonUseCase {

    operator fun invoke(persona: Persona, newPersona: Persona){
        Repository.updatePersona(persona, newPersona)
    }
}