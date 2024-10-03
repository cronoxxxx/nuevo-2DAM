package com.example.hospitalcrud.dao.repositories;

import com.example.hospitalcrud.dao.model.Credential;

public interface CredentialDAO {
    Credential get(Credential credential);
    boolean register(String username, String password);
}
