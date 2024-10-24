package com.example.apppersonas_adriansaavedra.domain.usecases.personas

import com.example.apppersonas_adriansaavedra.data.Repository


class GetPersonsUseCase {
    operator fun invoke () = Repository.getPersonas()

}
