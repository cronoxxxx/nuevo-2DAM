package com.example.hospitalcrud.domain.services;

import com.example.hospitalcrud.dao.model.Credential;
import com.example.hospitalcrud.dao.repositories.statics.CredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;

    public CredentialService() {
        this.credentialRepository = new CredentialRepository();
    }

    public boolean login(Credential userCredentials) {
       Credential credential = credentialRepository.get(userCredentials.getUserName());
        return credential != null;
    }
}
