package com.example.buzonfxspring_adriansaavedra.domain.validators;

import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import org.springframework.stereotype.Component;

@Component
public class GrupoValidator {
    public boolean validateGrupo (Grupo grupo){
        if (grupo.getNombre().isBlank() ||grupo.getNombre().isEmpty()){
            return false;
        } else return !grupo.getPassword().isBlank() && !grupo.getPassword().isEmpty();
    }
}
