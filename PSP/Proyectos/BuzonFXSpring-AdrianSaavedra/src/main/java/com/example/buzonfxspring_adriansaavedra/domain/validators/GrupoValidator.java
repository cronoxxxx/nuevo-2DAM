package com.example.buzonfxspring_adriansaavedra.domain.validators;

import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

@Component
public class GrupoValidator {
    public Either<String, Boolean> validateGrupo(Grupo grupo) {
        if (grupo.getNombre().isBlank()) {
            return Either.left("El nombre del grupo no puede estar vacío.");
        }
        if (grupo.getPassword().isBlank()) {
            return Either.left("La contraseña del grupo no puede estar vacía.");
        }
        return Either.right(true);
    }
}
