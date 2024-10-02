package com.example.misegundoproyecto_adriansaavedra.domain.usecases.personas

import com.example.misegundoproyecto_adriansaavedra.data.Repository

class DeletePersonUseCase {
    operator fun invoke (index:Int)=
        Repository.deletePersona(index)
}