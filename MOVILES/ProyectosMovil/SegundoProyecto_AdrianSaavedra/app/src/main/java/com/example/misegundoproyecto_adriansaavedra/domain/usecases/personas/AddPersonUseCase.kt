package com.example.misegundoproyecto_adriansaavedra.domain.usecases.personas

import com.example.misegundoproyecto_adriansaavedra.data.Repository
import com.example.misegundoproyecto_adriansaavedra.domain.modelo.Persona

class AddPersonUseCase {
    operator fun invoke(persona: Persona)=
        Repository.addPerson(persona)
}