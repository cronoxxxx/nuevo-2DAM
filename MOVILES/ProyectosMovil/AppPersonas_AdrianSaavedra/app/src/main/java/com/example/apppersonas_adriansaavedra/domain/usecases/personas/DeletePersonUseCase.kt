package com.example.apppersonas_adriansaavedra.domain.usecases.personas

import com.example.apppersonas_adriansaavedra.data.Repository
import com.example.apppersonas_adriansaavedra.domain.modelo.Persona


class DeletePersonUseCase {
    operator fun invoke (persona : Persona)=
        Repository.deletePersona(persona)
}