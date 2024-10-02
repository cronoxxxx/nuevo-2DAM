package com.example.misegundoproyecto_adriansaavedra.domain.usecases.personas

import com.example.misegundoproyecto_adriansaavedra.data.Repository

class GetPersonUseCase {

    operator fun invoke (id:Int) = Repository.getPersona(id)
}